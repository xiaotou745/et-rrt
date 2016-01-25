USE [RenRenDB]
GO

/****** Object:  Table [dbo].[TaskShareStatistics]    Script Date: 12/31/2015 16:27:28 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[TaskShareStatistics](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[TaskId] [bigint] NOT NULL,
	[ClienterId] [bigint] NOT NULL,
	[CreateDate] [datetime] NOT NULL,
 CONSTRAINT [PK_TaskShareStatistics] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'自增id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'TaskShareStatistics', @level2type=N'COLUMN',@level2name=N'Id'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'任务id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'TaskShareStatistics', @level2type=N'COLUMN',@level2name=N'TaskId'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'地推员id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'TaskShareStatistics', @level2type=N'COLUMN',@level2name=N'ClienterId'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'分享或下载的时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'TaskShareStatistics', @level2type=N'COLUMN',@level2name=N'CreateDate'
GO

ALTER TABLE [dbo].[TaskShareStatistics] ADD  CONSTRAINT [DF_TaskShareStatistics_CreateDate]  DEFAULT (getdate()) FOR [CreateDate]
GO


