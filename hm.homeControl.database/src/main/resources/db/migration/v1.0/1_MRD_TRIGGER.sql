/****** Object:  Trigger [dbo].[ON_CREATE_MRD]    Script Date: 18. 10. 2016 17:05:56 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

/********* Firstly create table for storing last statuses of machines **********/

CREATE TABLE [dbo].[Last_machine_status](
    [id] [int] NOT NULL,
    [retrieval_date] [datetime] NOT NULL,
    [fk_machine] [int] NOT NULL,
    [fk_prg_stat] [int] NOT NULL,
 CONSTRAINT [PK_Last_machine_status] PRIMARY KEY CLUSTERED
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[Last_machine_status]  WITH CHECK ADD  CONSTRAINT [FK_MACHINE_MACHINE_ID] FOREIGN KEY([fk_machine])
REFERENCES [dbo].[MachineExtended] ([id])
GO

ALTER TABLE [dbo].[Last_machine_status] CHECK CONSTRAINT [FK_MACHINE_MACHINE_ID]
GO

ALTER TABLE [dbo].[Last_machine_status]  WITH CHECK ADD  CONSTRAINT [FK_PRG_STAT_ENUM_VALUE] FOREIGN KEY([fk_prg_stat])
REFERENCES [dbo].[Enum_value] ([id])
GO

ALTER TABLE [dbo].[Last_machine_status] CHECK CONSTRAINT [FK_PRG_STAT_ENUM_VALUE]
GO

/********** Finally create trigger on machine raw data table ************/

-- =============================================
-- Author:        Jozef Macho
-- Create date: 18.10.2016
-- Description:    Trigger on MachineRawData table which occurs after inserting value into table and
--                creates new record in Last_machine_status table using id.
-- =============================================
CREATE TRIGGER [dbo].[ON_CREATE_MRD]
   ON  [dbo].[machine_raw_data]
   AFTER INSERT
AS
BEGIN
    -- SET NOCOUNT ON added to prevent extra result sets from
    -- interfering with SELECT statements.
    SET NOCOUNT ON;
    DELETE FROM [dbo].[Last_machine_status] where fk_machine = (SELECT fk_machineExtended FROM inserted);
    INSERT [dbo].[Last_machine_status]
    SELECT id,date,fk_machineExtended,prgstat FROM inserted;
    -- Insert statements for trigger here

END
