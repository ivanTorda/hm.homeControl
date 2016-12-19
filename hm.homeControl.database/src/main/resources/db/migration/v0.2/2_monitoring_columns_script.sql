USE [MACHINE_PRODUCTIVITY]
GO

SET IDENTITY_INSERT [dbo].[enum_type] ON 
INSERT [dbo].[enum_type] ([id], [name]) VALUES (4, N'monitoring')
SET IDENTITY_INSERT [dbo].[enum_type] OFF
GO

SET IDENTITY_INSERT [dbo].[enum_value] ON 
GO
INSERT [dbo].[enum_value] ([id], [value], [fk_enum_type]) VALUES (34, N'heidenhain', 4)
GO
INSERT [dbo].[enum_value] ([id], [value], [fk_enum_type]) VALUES (35, N'semaphore', 4)
GO
INSERT [dbo].[enum_value] ([id], [value], [fk_enum_type]) VALUES (36, N'manual', 4)
GO
INSERT [dbo].[enum_value] ([id], [value], [fk_enum_type]) VALUES (37, N'none', 4)
GO
SET IDENTITY_INSERT [dbo].[enum_value] OFF
GO

ALTER TABLE [dbo].[machine] ADD [data_monitored] [int] NOT NULL DEFAULT 37
GO
ALTER TABLE [dbo].[machine] WITH CHECK ADD  CONSTRAINT [data_monitor_enum_value] FOREIGN KEY([data_monitored])
REFERENCES [dbo].[enum_value] ([id])
GO
ALTER TABLE [dbo].[machine] CHECK CONSTRAINT [data_monitor_enum_value]
GO

ALTER TABLE [dbo].[machine] ADD [uptime_monitored] [int] NOT NULL DEFAULT 36
GO
ALTER TABLE [dbo].[machine] WITH CHECK ADD  CONSTRAINT [uptime_monitor_enum_value] FOREIGN KEY([uptime_monitored])
REFERENCES [dbo].[enum_value] ([id])
GO
ALTER TABLE [dbo].[machine] CHECK CONSTRAINT [uptime_monitor_enum_value]
GO