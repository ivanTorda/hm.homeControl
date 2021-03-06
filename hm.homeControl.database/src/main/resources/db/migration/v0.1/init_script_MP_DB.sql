/****** Object:  Database [MACHINE_PRODUCTIVITY]    ******/
CREATE DATABASE [MACHINE_PRODUCTIVITY]
GO

USE [MACHINE_PRODUCTIVITY]
GO

/****** Object:  Table [dbo].[category]    ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[category](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) NOT NULL,
	[description] [varchar](max) NULL,
	[parent] [int] NULL,
 CONSTRAINT [PK_category] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[category_machine]    Script Date: 3/4/2015 11:30:25 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[category_machine](
	[fk_category] [int] NOT NULL,
	[fk_machine] [int] NOT NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[enum_type]    Script Date: 3/4/2015 11:30:25 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[enum_type](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) NOT NULL,
 CONSTRAINT [PK_enum type] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[enum_value]    Script Date: 3/4/2015 11:30:25 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[enum_value](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[value] [varchar](50) NOT NULL,
	[fk_enum_type] [int] NOT NULL,
 CONSTRAINT [PK_enum value] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[machine]    Script Date: 3/4/2015 11:30:25 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[machine](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[ip] [varchar](50) NOT NULL,
	[name] [varchar](50) NOT NULL,
	[image_src] [varchar](50) NOT NULL,
	[override_correction] [int] NOT NULL,
	[timeout_count] [int] NOT NULL DEFAULT 0,
	[last_retrieval] [datetime] NULL,
	[active] [bit] NOT NULL,
	[controller] [varchar](50) NOT NULL,
 CONSTRAINT [PK_machine] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[machine_category]    Script Date: 3/4/2015 11:30:25 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[machine_category](
	[machine_id] [int] NOT NULL,
	[categoryList_id] [int] NOT NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[machine_raw_data]    Script Date: 3/4/2015 11:30:25 AM ******/
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
	[fk_machine] [int] NOT NULL,
	[emode] [int] NULL,
	[prgstat] [int] NULL,
 CONSTRAINT [PK_machine_raw_data] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[machine_statistic]    Script Date: 3/4/2015 11:30:25 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[machine_statistic](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[fk_machine] [int] NOT NULL,
	[idle_time] [int] NOT NULL,
	[started_time] [int] NOT NULL,
	[error_time] [int] NOT NULL,
	[no_data_time] [int] NOT NULL,
	[stop_time] [int] NOT NULL,
	[interupt_time] [int] NOT NULL,
	[disconnect_time] [int] NOT NULL,
	[idle_percentage] [real] NOT NULL,
	[started_percentage] [real] NOT NULL,
	[error_percentage] [real] NOT NULL,
	[no_data_percentage] [real] NOT NULL,
	[stop_percentage] [real] NOT NULL,
	[interupt_percentage] [real] NOT NULL,
	[disconnect_percentage] [real] NOT NULL,
 CONSTRAINT [PK_machine_statistic] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET IDENTITY_INSERT [dbo].[enum_type] ON 
INSERT [dbo].[enum_type] ([id], [name]) VALUES (1, N'machine_status')
INSERT [dbo].[enum_type] ([id], [name]) VALUES (2, N'machine_mode')
INSERT [dbo].[enum_type] ([id], [name]) VALUES (3, N'controller')
SET IDENTITY_INSERT [dbo].[enum_type] OFF
SET IDENTITY_INSERT [dbo].[enum_value] ON 
GO
INSERT [dbo].[enum_value] ([id], [value], [fk_enum_type]) VALUES (1, N'running', 1)
GO
INSERT [dbo].[enum_value] ([id], [value], [fk_enum_type]) VALUES (2, N'idle', 1)
GO
INSERT [dbo].[enum_value] ([id], [value], [fk_enum_type]) VALUES (3, N'not_selected', 1)
GO
INSERT [dbo].[enum_value] ([id], [value], [fk_enum_type]) VALUES (4, N'no_data', 1)
GO
INSERT [dbo].[enum_value] ([id], [value], [fk_enum_type]) VALUES (5, N'interrupted', 1)
GO
INSERT [dbo].[enum_value] ([id], [value], [fk_enum_type]) VALUES (6, N'stopped', 1)
GO
INSERT [dbo].[enum_value] ([id], [value], [fk_enum_type]) VALUES (7, N'finished', 1)
GO
INSERT [dbo].[enum_value] ([id], [value], [fk_enum_type]) VALUES (8, N'error', 1)
GO
INSERT [dbo].[enum_value] ([id], [value], [fk_enum_type]) VALUES (9, N'unexpected_error', 1)
GO
INSERT [dbo].[enum_value] ([id], [value], [fk_enum_type]) VALUES (10, N'automatic',2)
GO
INSERT [dbo].[enum_value] ([id], [value], [fk_enum_type]) VALUES (11, N'manual',2)
GO
INSERT [dbo].[enum_value] ([id], [value], [fk_enum_type]) VALUES (12, N'handwheel',2)
GO
INSERT [dbo].[enum_value] ([id], [value], [fk_enum_type]) VALUES (13, N'mdi',2)
GO
INSERT [dbo].[enum_value] ([id], [value], [fk_enum_type]) VALUES (14, N'rpf',2)
GO
INSERT [dbo].[enum_value] ([id], [value], [fk_enum_type]) VALUES (15, N'simulo_turbo',2)
GO
INSERT [dbo].[enum_value] ([id], [value], [fk_enum_type]) VALUES (16, N'singlestep',2)
GO
INSERT [dbo].[enum_value] ([id], [value], [fk_enum_type]) VALUES (17, N'other',2)
GO
INSERT [dbo].[enum_value] ([id], [value], [fk_enum_type]) VALUES (18, N'none',3)
GO
INSERT [dbo].[enum_value] ([id], [value], [fk_enum_type]) VALUES (19, N'atekm',3)
GO
INSERT [dbo].[enum_value] ([id], [value], [fk_enum_type]) VALUES (20, N'itnc', 3)
GO
INSERT [dbo].[enum_value] ([id], [value], [fk_enum_type]) VALUES (21, N'mill_plus', 3)
GO
INSERT [dbo].[enum_value] ([id], [value], [fk_enum_type]) VALUES (22, N'mill_plus_it', 3)
GO
INSERT [dbo].[enum_value] ([id], [value], [fk_enum_type]) VALUES (23, N'turn_plus', 3)
GO
INSERT [dbo].[enum_value] ([id], [value], [fk_enum_type]) VALUES (24, N'mill_plus_it_nck', 3)
GO
INSERT [dbo].[enum_value] ([id], [value], [fk_enum_type]) VALUES (25, N'manual_plus_nck', 3)
GO
INSERT [dbo].[enum_value] ([id], [value], [fk_enum_type]) VALUES (26, N'atekm_nck', 3)
GO
INSERT [dbo].[enum_value] ([id], [value], [fk_enum_type]) VALUES (27, N'tnc320_nck', 3)
GO
INSERT [dbo].[enum_value] ([id], [value], [fk_enum_type]) VALUES (28, N'grind_plus_nck', 3)
GO
INSERT [dbo].[enum_value] ([id], [value], [fk_enum_type]) VALUES (29, N'tnc6xx_nck', 3)
GO
INSERT [dbo].[enum_value] ([id], [value], [fk_enum_type]) VALUES (30, N'ar6000_nck', 3)
GO
INSERT [dbo].[enum_value] ([id], [value], [fk_enum_type]) VALUES (31, N'cnc_pilot6xx_nck', 3)
GO
INSERT [dbo].[enum_value] ([id], [value], [fk_enum_type]) VALUES (32, N'tnc128_nck', 3)
GO
INSERT [dbo].[enum_value] ([id], [value], [fk_enum_type]) VALUES (33, N'grind_plus640_nck', 3)
GO
SET IDENTITY_INSERT [dbo].[enum_value] OFF
GO
ALTER TABLE [dbo].[machine] ADD  CONSTRAINT [DF_Machine_override_correction]  DEFAULT ((1)) FOR [override_correction]
GO
ALTER TABLE [dbo].[machine] ADD  DEFAULT ('itnc') FOR [controller]
GO
ALTER TABLE [dbo].[category]  WITH CHECK ADD  CONSTRAINT [FK_category_category1] FOREIGN KEY([parent])
REFERENCES [dbo].[category] ([id])
GO
ALTER TABLE [dbo].[category] CHECK CONSTRAINT [FK_category_category1]
GO
ALTER TABLE [dbo].[category_machine]  WITH CHECK ADD  CONSTRAINT [FK_category_machine_category] FOREIGN KEY([fk_category])
REFERENCES [dbo].[category] ([id])
GO
ALTER TABLE [dbo].[category_machine] CHECK CONSTRAINT [FK_category_machine_category]
GO
ALTER TABLE [dbo].[category_machine]  WITH CHECK ADD  CONSTRAINT [FK_category_machine_machine] FOREIGN KEY([fk_machine])
REFERENCES [dbo].[machine] ([id])
GO
ALTER TABLE [dbo].[category_machine] CHECK CONSTRAINT [FK_category_machine_machine]
GO
ALTER TABLE [dbo].[enum_value]  WITH CHECK ADD  CONSTRAINT [FK_enum value_enum type] FOREIGN KEY([fk_enum_type])
REFERENCES [dbo].[enum_type] ([id])
GO
ALTER TABLE [dbo].[enum_value] CHECK CONSTRAINT [FK_enum value_enum type]
GO
ALTER TABLE [dbo].[machine_category]  WITH CHECK ADD  CONSTRAINT [FK_qrslpeppjdpbyweitgcwbodnx] FOREIGN KEY([categoryList_id])
REFERENCES [dbo].[category] ([id])
GO
ALTER TABLE [dbo].[machine_category] CHECK CONSTRAINT [FK_qrslpeppjdpbyweitgcwbodnx]
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
ALTER TABLE [dbo].[machine_raw_data]  WITH CHECK ADD  CONSTRAINT [FK_machine_raw_data_Machine] FOREIGN KEY([fk_machine])
REFERENCES [dbo].[machine] ([id])
GO
ALTER TABLE [dbo].[machine_raw_data] CHECK CONSTRAINT [FK_machine_raw_data_Machine]
GO
ALTER TABLE [dbo].[machine_statistic]  WITH CHECK ADD  CONSTRAINT [FK_machine_statistic_Machine] FOREIGN KEY([fk_machine])
REFERENCES [dbo].[machine] ([id])
GO
ALTER TABLE [dbo].[machine_statistic] CHECK CONSTRAINT [FK_machine_statistic_Machine]
GO
USE [master]
GO


