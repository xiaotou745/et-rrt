USE [RenRenDB]
GO
/****** Object:  Table [dbo].[ClienterLog]    Script Date: 09/25/2015 15:38:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ClienterLog](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[ClienterId] [bigint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[OptName] [nvarchar](50) NOT NULL,
	[Remark] [nvarchar](max) NOT NULL,
 CONSTRAINT [PK_ClienterLog] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'C端ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ClienterLog', @level2type=N'COLUMN',@level2name=N'ClienterId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'操作时间 ' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ClienterLog', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'操作人' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ClienterLog', @level2type=N'COLUMN',@level2name=N'OptName'
GO
ALTER TABLE [dbo].[ClienterLog] ADD  CONSTRAINT [DF_ClienterLog_CreateTime]  DEFAULT (getdate()) FOR [CreateTime]
GO
ALTER TABLE [dbo].[ClienterLog] ADD  CONSTRAINT [DF_ClienterLog_OptName]  DEFAULT ('') FOR [OptName]
GO
