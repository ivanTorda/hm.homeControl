USE [MACHINE_PRODUCTIVITY]
GO

ALTER TABLE [dbo].[machine] ADD [serial_number] [varchar](50) NOT NULL DEFAULT 'DEFAULT';
GO

