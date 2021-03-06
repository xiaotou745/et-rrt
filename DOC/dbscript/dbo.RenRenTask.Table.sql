USE [RenRenDB]
GO
/****** Object:  Table [dbo].[RenRenTask]    Script Date: 09/25/2015 15:38:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RenRenTask](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[TaskTitle] [nvarchar](200) NOT NULL,
	[TaskNotice] [nvarchar](1000) NOT NULL,
	[TaskGeneralInfo] [nvarchar](max) NOT NULL,
	[BusinessId] [bigint] NOT NULL,
	[Pusher] [nvarchar](50) NOT NULL,
	[CreateName] [nvarchar](50) NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[ModifyName] [nvarchar](50) NOT NULL,
	[ModifyTime] [datetime] NOT NULL,
	[BeginTime] [datetime] NOT NULL,
	[EndTiime] [datetime] NOT NULL,
	[TaskCycle] [float] NOT NULL,
	[AvailableCount] [int] NOT NULL,
	[Amount] [decimal](18, 2) NOT NULL,
	[State] [int] NOT NULL,
	[TaskToatlCount] [int] NOT NULL,
	[TempateId] [bigint] NOT NULL,
	[Link] [nvarchar](500) NOT NULL,
	[PaymentMethod] [smallint] NOT NULL,
 CONSTRAINT [PK_RenRenTask] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'任务公告' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'RenRenTask', @level2type=N'COLUMN',@level2name=N'TaskNotice'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'任务描述' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'RenRenTask', @level2type=N'COLUMN',@level2name=N'TaskGeneralInfo'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'商家Id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'RenRenTask', @level2type=N'COLUMN',@level2name=N'BusinessId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'发布商名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'RenRenTask', @level2type=N'COLUMN',@level2name=N'Pusher'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建人' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'RenRenTask', @level2type=N'COLUMN',@level2name=N'CreateName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'RenRenTask', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'修改人' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'RenRenTask', @level2type=N'COLUMN',@level2name=N'ModifyName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'修改时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'RenRenTask', @level2type=N'COLUMN',@level2name=N'ModifyTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'任务开始时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'RenRenTask', @level2type=N'COLUMN',@level2name=N'BeginTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'任务结束时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'RenRenTask', @level2type=N'COLUMN',@level2name=N'EndTiime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'任务周期' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'RenRenTask', @level2type=N'COLUMN',@level2name=N'TaskCycle'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'任务剩余量' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'RenRenTask', @level2type=N'COLUMN',@level2name=N'AvailableCount'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'任务单价' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'RenRenTask', @level2type=N'COLUMN',@level2name=N'Amount'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'任务状态，默认有效1，0无效' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'RenRenTask', @level2type=N'COLUMN',@level2name=N'State'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'任务总量' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'RenRenTask', @level2type=N'COLUMN',@level2name=N'TaskToatlCount'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'模板Id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'RenRenTask', @level2type=N'COLUMN',@level2name=N'TempateId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'任务链接' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'RenRenTask', @level2type=N'COLUMN',@level2name=N'Link'
GO
ALTER TABLE [dbo].[RenRenTask] ADD  CONSTRAINT [DF_RenRenTask_Notice]  DEFAULT ('') FOR [TaskNotice]
GO
ALTER TABLE [dbo].[RenRenTask] ADD  CONSTRAINT [DF_RenRenTask_CreateName]  DEFAULT ('') FOR [CreateName]
GO
ALTER TABLE [dbo].[RenRenTask] ADD  CONSTRAINT [DF_RenRenTask_CreateTime]  DEFAULT (getdate()) FOR [CreateTime]
GO
ALTER TABLE [dbo].[RenRenTask] ADD  CONSTRAINT [DF_RenRenTask_ModifyName]  DEFAULT ('') FOR [ModifyName]
GO
ALTER TABLE [dbo].[RenRenTask] ADD  CONSTRAINT [DF_RenRenTask_TaskCycle]  DEFAULT ((0)) FOR [TaskCycle]
GO
ALTER TABLE [dbo].[RenRenTask] ADD  CONSTRAINT [DF_RenRenTask_AvailableCount]  DEFAULT ((0)) FOR [AvailableCount]
GO
ALTER TABLE [dbo].[RenRenTask] ADD  CONSTRAINT [DF_RenRenTask_Amount]  DEFAULT ((0)) FOR [Amount]
GO
ALTER TABLE [dbo].[RenRenTask] ADD  CONSTRAINT [DF_RenRenTask_State]  DEFAULT ((1)) FOR [State]
GO
ALTER TABLE [dbo].[RenRenTask] ADD  CONSTRAINT [DF_RenRenTask_TaskToatlCount]  DEFAULT ((0)) FOR [TaskToatlCount]
GO
ALTER TABLE [dbo].[RenRenTask] ADD  CONSTRAINT [DF_RenRenTask_TempateId]  DEFAULT ((0)) FOR [TempateId]
GO
ALTER TABLE [dbo].[RenRenTask] ADD  CONSTRAINT [DF_RenRenTask_Link]  DEFAULT ('') FOR [Link]
GO
ALTER TABLE [dbo].[RenRenTask] ADD  CONSTRAINT [DF_RenRenTask_PaymentMethod]  DEFAULT ((1)) FOR [PaymentMethod]
GO
