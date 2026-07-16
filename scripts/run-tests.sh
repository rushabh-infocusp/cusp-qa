#!/usr/bin/env bash
# Boots the Android emulator if it isn't already running, waits until it's
# genuinely ready (not just sys.boot_completed), then runs the given TestNG
# suite via Maven. Usage: scripts/run-tests.sh [suite] [extra mvn args...]
set -euo pipefail

SUITE="${1:-smoke}"
[ $# -gt 0 ] && shift

AVD_NAME="${AVD_NAME:-RushabhDevice}"
DEVICE_UDID="${DEVICE_UDID:-emulator-5554}"
BOOT_TIMEOUT_SECS="${BOOT_TIMEOUT_SECS:-120}"

is_device_online() {
    adb devices | awk -v udid="$DEVICE_UDID" '$1 == udid && $2 == "device" { found=1 } END { exit !found }'
}

wait_for_boot() {
    local waited=0
    echo "Waiting for $DEVICE_UDID to come online..."
    until adb -s "$DEVICE_UDID" get-state >/dev/null 2>&1; do
        sleep 2
        waited=$((waited + 2))
        if [ "$waited" -ge "$BOOT_TIMEOUT_SECS" ]; then
            echo "Timed out waiting for device to appear" >&2
            exit 1
        fi
    done

    echo "Waiting for boot to complete..."
    until [ "$(adb -s "$DEVICE_UDID" shell getprop sys.boot_completed 2>/dev/null | tr -d '\r')" = "1" ]; do
        sleep 2
        waited=$((waited + 2))
        if [ "$waited" -ge "$BOOT_TIMEOUT_SECS" ]; then
            echo "Timed out waiting for sys.boot_completed" >&2
            exit 1
        fi
    done

    # sys.boot_completed flips before the package manager and UI are actually
    # ready to serve instrumentation — probing pm avoids the UiAutomator2
    # "instrumentation process cannot be initialized" timeout on a fresh boot.
    echo "Waiting for package manager to be ready..."
    until adb -s "$DEVICE_UDID" shell pm list packages >/dev/null 2>&1; do
        sleep 2
        waited=$((waited + 2))
        if [ "$waited" -ge "$BOOT_TIMEOUT_SECS" ]; then
            echo "Timed out waiting for package manager" >&2
            exit 1
        fi
    done

    sleep 5
    echo "Device ready."
}

if is_device_online; then
    echo "Device $DEVICE_UDID already online."
else
    echo "Booting emulator $AVD_NAME..."
    nohup emulator -avd "$AVD_NAME" -no-snapshot-load > /tmp/emulator-"$AVD_NAME".log 2>&1 &
    disown
    wait_for_boot
fi

mvn test -Dsuite="$SUITE" "$@"
