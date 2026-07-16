---
name: test-case-writer
description: Writes and consolidates manual test cases for Cusp app modules (mutual funds, transactions, wealth, onboarding, KYC). Use when the user asks to write, expand, or consolidate test cases.
---

# Test Case Writer

Structure every test case as:
- Title (sentence case)
- Preconditions
- Numbered steps
- Expected results

Cap each section at 5 lines. Group related test cases by module/flow.

Always include negative and edge cases by default (invalid input, boundary values, error/failure states, interrupted flows), not just the happy path.

After presenting the test cases, always ask the user whether the output should be exported as CSV (columns: TC ID, Title, Preconditions, Steps, Expected Results, per CLAUDE.md).
