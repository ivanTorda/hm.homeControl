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
    [fk_machine] [int] NOT NULL,
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
ALTER TABLE [dbo].[service_event]  WITH CHECK ADD  CONSTRAINT [FK_ServiceEvent_Machine] FOREIGN KEY([fk_machine])
REFERENCES [dbo].[machine] ([id])
GO
ALTER TABLE [dbo].[service_event] CHECK CONSTRAINT [FK_ServiceEvent_Machine]
GO
ALTER TABLE [dbo].[service_event_history]  WITH CHECK ADD  CONSTRAINT [FK_ServiceEventHistory_ServiceEvent] FOREIGN KEY([service_event])
REFERENCES [dbo].[service_event] ([id])
GO
ALTER TABLE [dbo].[service_event_history] CHECK CONSTRAINT [FK_ServiceEventHistory_ServiceEvent]
GO