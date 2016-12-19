/**************************************************************
**************** CREATE TABLE FOR UPTIME DATA *****************
***************************************************************/

USE [MACHINE_PRODUCTIVITY]
GO

CREATE TABLE [dbo].[uptime_data](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[retrieval_date] [datetime] NOT NULL,
	[running_time] [int] NOT NULL,
	[uptime] [int] NOT NULL,
	[spindle_running_time_1] [int] NOT NULL,
	[spindle_running_time_2] [int] NULL,
	[author] [varchar](50) NOT NULL,
	[fk_machine] [int] NOT NULL,
 CONSTRAINT [PK_uptime] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[uptime_data] WITH CHECK ADD  CONSTRAINT [FK_uptime_data_Machine] FOREIGN KEY([fk_machine])
REFERENCES [dbo].[machine] ([id])
GO
ALTER TABLE [dbo].[uptime_data] CHECK CONSTRAINT [FK_uptime_data_Machine]
GO

/**************************************************************
**************** ADD COLUMN [SPINDLE] TO MACHINE **************
***************************************************************/

ALTER TABLE [dbo].[machine] ADD [spindles] [int] NOT NULL DEFAULT 1