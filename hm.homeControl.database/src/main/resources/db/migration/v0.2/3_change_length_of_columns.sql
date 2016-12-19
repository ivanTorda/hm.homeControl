/****** Enlarge the length of program columns of machine raw data ******/

USE [MACHINE_PRODUCTIVITY]
GO

ALTER TABLE [MACHINE_PRODUCTIVITY].[dbo].[machine_raw_data]
  ALTER COLUMN [selprg] VARCHAR (100) NULL
  GO
  
ALTER TABLE [MACHINE_PRODUCTIVITY].[dbo].[machine_raw_data]
	ALTER COLUMN [exepnt] VARCHAR (100) NULL
  GO