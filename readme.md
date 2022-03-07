# GHCO Application

## Tasks

The purpose of this exercise is to create an application to read the simplified trade booking information in the CSV file and process them for intraday position aggregations.

The aggregation should extract cash positions (P&L) per BBGCode and per portfolio.

The position results should be verified by unit testing and displayed.

User Story: As a developer, I want to...

- Process all New trades and display the position aggregation results;

- Create an interactive way to add new trades, on top of what is loaded by the csv;

- Process all actions in the trades.csv file i.e. New, Amend and Cancel and display the results of these actions in the position aggregation results;


## Notes

For the sake of speed, I will use regular I/O, however if we wanted to expand this, and we expected data to come in this format, we could use spring batch

Research TestContainers/Localstack


line 62061 was an incorrect DateTime, so i manually fixed it for now

## Architecture

On startup:

- CommandLineRunner reads file
- File is ingested, a class is formed and then stored in the ledger
- Aggregations are functions that can be called on the ledger and we will expose them via API
- API will be able to add new trade


## Domain

The following information is speculative, but these are assumptions for modelling im taking.

From what I can tell:

Portfolio, account, user

Account has a list of portfolios
User has a list of accounts

The ledger is a map of all users

To CANCEL an order means the same everything, just the status is to cancel.

To amend

## OrderBook Modelling

- A stock book has side, price, volume, action and tradetimeUTC in there.
- An orderBook has many different codes in there Map<BBGCode, StockBook>
- A portfolio has many currencies-> Map<currency, OrderBook>

## Account Modelling

- An account “Account” has several portfolios -> Map<portfolioName, Portfolio>
- A User seems to have many accounts Map<accountName, Account>
- Ledger -> Map<Username, User>

I don’t know what I should do with strategy….so I will ignore for now

BBGCode, Currency,Side, Price, Volume, Action, TradeTimeUTC seem to be useful for this exercise


## Product Actions

- Serialise the data into the model
- Expose an API with some of the actions demanded from the tasks
- Write functions to aggregate some of the values in the orderbook




