/****** Object:  Table [dbo].[machine_raw_data]    Script Date: 3/4/2015 11:30:25 AM ******/
USE MACHINE_PRODUCTIVITY_MD
drop table [dbo].machine_raw_data

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[machine_raw_data](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[date] [datetime] NOT NULL,
	[exepnt] [varchar](50) NULL,
	[prgpos] [varchar](50) NULL,
	[selprg] [varchar](50) NULL,
	[feedovrd] [real] NULL,
	[speedovrd] [real] NULL,
	[rapidovrd] [real] NULL,
	[fk_machineExtended] [int] NOT NULL,
	[emode] [int] NULL,
	[prgstat] [int] NULL,
 CONSTRAINT [PK_machine_raw_data] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
ALTER TABLE [dbo].[machine_raw_data]  WITH CHECK ADD  CONSTRAINT [FK_machine_raw_data_enum_value] FOREIGN KEY([emode])
REFERENCES [dbo].[enum_value] ([id])
GO
ALTER TABLE [dbo].[machine_raw_data] CHECK CONSTRAINT [FK_machine_raw_data_enum_value]
GO

ALTER TABLE [dbo].[machine_raw_data]  WITH CHECK ADD  CONSTRAINT [FK_machine_raw_data_enum_value1] FOREIGN KEY([prgstat])
REFERENCES [dbo].[enum_value] ([id])
GO
ALTER TABLE [dbo].[machine_raw_data] CHECK CONSTRAINT [FK_machine_raw_data_enum_value1]
GO

ALTER TABLE [dbo].[machine_raw_data]  WITH CHECK ADD  CONSTRAINT [FK_machine_raw_data_MachineExtended] FOREIGN KEY([fk_machineExtended])
REFERENCES [dbo].[MachineExtended] ([id])
GO
ALTER TABLE [dbo].[machine_raw_data] CHECK CONSTRAINT [FK_machine_raw_data_MachineExtended]


drop table [dbo].uptime_data

GO
/****** Object:  Table [dbo].[uptime_data]    Script Date: 23. 11. 2015 14:41:47 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[uptime_data](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[author] [varchar](50) NOT NULL,
	[retrieval_date] [datetime] NULL,
	[running_time] [int] NOT NULL,
	[spindle_running_time_1] [int] NOT NULL,
	[spindle_running_time_2] [int] NULL,
	[uptime] [int] NOT NULL,
	[fk_machineExtended] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[uptime_data]  WITH CHECK ADD  CONSTRAINT [FK_asbercnm4p1hwoymy140y25fn] FOREIGN KEY([fk_machineExtended])
REFERENCES [dbo].[MachineExtended] ([id])
GO
ALTER TABLE [dbo].[uptime_data] CHECK CONSTRAINT [FK_asbercnm4p1hwoymy140y25fn]
GO

drop table service_event_history
drop table service_event

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[service_event](
    [id] [int] IDENTITY(1,1) NOT NULL,
    [init_uptime] [int] NOT NULL,
    [last_occured_date] [datetime] NULL,
    [last_occured_uptime] [int] NULL,
    [name] [varchar](50) NOT NULL,
    [occurs_every] [int] NOT NULL,
    [reminder] [int] NOT NULL,
    [performed] [bit] NULL,
    [fk_machineExtended] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[service_event_history](
    [id] [int] IDENTITY(1,1) NOT NULL,
    [comment] [varchar](1000) NULL,
    [date_performed] [datetime] NULL,
    [occured_uptime] [int] NULL,
    [performed] [bit] NULL,
    [service_event] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[service_event]  WITH CHECK ADD  CONSTRAINT [FK_ServiceEvent_MachineExtended] FOREIGN KEY([fk_machineExtended])
REFERENCES [dbo].[machineExtended] ([id])
GO
ALTER TABLE [dbo].[service_event] CHECK CONSTRAINT [FK_ServiceEvent_MachineExtended]
GO
ALTER TABLE [dbo].[service_event_history]  WITH CHECK ADD  CONSTRAINT [FK_ServiceEventHistory_ServiceEvent] FOREIGN KEY([service_event])
REFERENCES [dbo].[service_event] ([id])
GO
ALTER TABLE [dbo].[service_event_history] CHECK CONSTRAINT [FK_ServiceEventHistory_ServiceEvent]
GO