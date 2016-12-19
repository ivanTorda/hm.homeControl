USE [MACHINE_PRODUCTIVITY]
GO
CREATE NONCLUSTERED INDEX [date-machine_index]
ON [dbo].[machine_raw_data] ([date],[fk_machine])
INCLUDE ([id],[exepnt],[prgpos],[selprg],[feedovrd],[speedovrd],[rapidovrd],[emode],[prgstat])
GO