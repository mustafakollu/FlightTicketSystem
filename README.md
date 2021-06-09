# FlightTicketSystem
AIM:
In this experiment, you are expected to develop a flight search engine (such as skyscanner
(www.skayscanner.com/) or e-dream (www.edreams.com/)) that enables users to make flight plans. The
flight database will be provided via two input files. Your program should read these input files and create a
directed graph structure to store the information. Finally, your program should create output files according
to information given two input files. In this section, details will be given about the input files to be used
and the output file to be created.

COMMANDS:
List All

List all command is used for listing all the possible flight plan(s) from departure point to the arrival point.
Structure of command
[listAll] tab [dept]-> [arr] tab [start_date]

List Proper

List proper command is used for listing all the proper flight plan(s) from departure point to the arrival
point which means a flight plan should be removed from the resulting flight plan set if there is another
flight plan which is both quicker (that arrives at the final destination earlier) and cheaper.
Structure of command
[listProper] tab [dept]-> [arr] tab [start_date]

List Cheapest

List cheapest command is used for listing the cheapest possible flight plan(s) from departure point to the
arrival point. Keep in mind that there may be more than one possible cheapest flight plans.
Structure of command
[listCheapest] tab [dept]-> [arr] tab [start_date]

List Quickest

List quickest command is used for listing the quickest possible flight plan(s) from departure point to the
arrival point. Keep in mind that there may be more than one possible quickest flight plans.
Structure of command
[listQuickest] tab [dept]-> [arr] tab [start_date]

List Cheaper

List cheaper command is used for listing all the proper flight plans from departure point to the arrival
point that are cheaper than given parameter.
Structure of command
[listCheaper] tab [dept]-> [arr] tab [start_date] tab [max_price]

List Quicker

List quicker command is used for listing all the proper flight plans from departure point to the arrival
point that arrive to the destination earlier than given parameter.
Structure of command
[listQuicker] tab [dept]-> [arr] tab [start_date] tab [latest_date_time]

List Excluding

List excluding command is used for listing all the proper flight plans from departure point to the arrival
point that do not involve a flight from given airlines company.
Structure of command
[listExcluding] tab [dept]-> [arr] tab [start_date] tab [company]

List Only From

List excluding command is used for listing all the proper flight plans from departure point to the arrival
point that consists of flights only from the given airlines company.
Structure of command
[listOnlyFrom] tab [dept]-> [arr] tab [start_date] tab [company]
