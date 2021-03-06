USE [mp_new]
GO
/****** Object:  Table [dbo].[Activity]    Script Date: 4. 3. 2016 11:05:46 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Activity](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) NOT NULL,
	[fromDate] [datetime] NOT NULL,
	[toDate] [datetime] NOT NULL,
	[userId] [varchar](50) NOT NULL,
	[fk_machine] [int] NOT NULL,
 CONSTRAINT [PK_Activity] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[Activity]  WITH CHECK ADD  CONSTRAINT [FK_Activity_Machine] FOREIGN KEY([fk_machine])
REFERENCES [dbo].[MachineExtended] ([id])
GO
ALTER TABLE [dbo].[Activity] CHECK CONSTRAINT [FK_Activity_Machine]
GO
