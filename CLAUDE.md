# Cusp QA Conventions

App: Cusp (com.infocusp.cuspmoney) — Onboarding, Bank Accounts, Home, Wealth, Transactions, Investments, Settings, Family Member, Cash flow, Recurring Transactions, Mutual Funds, Stocks and ETFs and NPS.

## Bug reports (ClickUp, CM-XXXX format)
- Titles and headings: sentence case, not title case
- Structure: concise title + steps to reproduce + expected result

## Test cases
- Structure: title + preconditions + numbered steps + expected results
- Cap each section at 5 lines
- Regression suites: export as CSV with columns: TC ID, Title, Preconditions, Steps, Expected Results

## Modules
Connect Bank Accounts,
Connect Investments (Mutual Funds, Stocks & ETFs, NPS), 
Account Aggregators (Finvu, NADL, One Money)
Transactions (tagging, merchant, bookmarking, exclude or include from transactions, notes, filters, search, payment history, context menu, bulk edit transactions), 
Onboarding, Home screen, Wealth screen, Settings, Investor Profile/KYC/CAN/Mandate.
