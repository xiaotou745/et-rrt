USE [RenRenDB]
GO
/****** Object:  Table [dbo].[OrderLog]    Script Date: 09/25/2015 15:38:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderLog](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[OrderId] [bigint] NOT NULL,
	[OptType] [smallint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[OptName] [nvarchar](50) NOT NULL,
	[Remark] [nvarchar](max) NOT NULL,
 CONSTRAINT [PK_OrderLog] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'订单ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'OrderLog', @level2type=N'COLUMN',@level2name=N'OrderId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'操作类型：1抢单2提交审核3重复提交审核4取消订单5超时取消（系统）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'OrderLog', @level2type=N'COLUMN',@level2name=N'OptType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'操作时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'OrderLog', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'操作人' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'OrderLog', @level2type=N'COLUMN',@level2name=N'OptName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'订单日志备注' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'OrderLog', @level2type=N'COLUMN',@level2name=N'Remark'
GO
ALTER TABLE [dbo].[OrderLog] ADD  CONSTRAINT [DF_OrderLog_CreateTime]  DEFAULT (getdate()) FOR [CreateTime]
GO
ALTER TABLE [dbo].[OrderLog] ADD  CONSTRAINT [DF_OrderLog_OptName]  DEFAULT ('') FOR [OptName]
GO
ALTER TABLE [dbo].[OrderLog] ADD  CONSTRAINT [DF_OrderLog_Remark]  DEFAULT ('') FOR [Remark]
GO
