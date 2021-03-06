USE [RenRenDB]
GO
/****** Object:  Table [dbo].[Order]    Script Date: 09/25/2015 15:38:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[ClienterId] [bigint] NOT NULL,
	[TaskId] [bigint] NOT NULL,
	[OrderStatus] [smallint] NOT NULL,
	[AuditStatus] [smallint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[FinishTime] [datetime] NULL,
	[AuditTime] [datetime] NULL,
	[AuditName] [datetime] NOT NULL,
	[DeadLineTime] [datetime] NOT NULL,
	[CancelTime] [datetime] NULL,
	[CancelName] [nvarchar](50) NOT NULL,
	[CancelRemark] [nvarchar](max) NOT NULL,
 CONSTRAINT [PK_Order] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'1已完成，2已取消，3超时自动取消，默认0进行中' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Order', @level2type=N'COLUMN',@level2name=N'OrderStatus'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'1审核中，2审核通过，3审核拒绝，默认0待审核' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Order', @level2type=N'COLUMN',@level2name=N'AuditStatus'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'抢单时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Order', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'完成时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Order', @level2type=N'COLUMN',@level2name=N'FinishTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'审核时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Order', @level2type=N'COLUMN',@level2name=N'AuditTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'审核人' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Order', @level2type=N'COLUMN',@level2name=N'AuditName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'截至时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Order', @level2type=N'COLUMN',@level2name=N'DealLineTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'取消时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Order', @level2type=N'COLUMN',@level2name=N'CancelTime'
GO
ALTER TABLE [dbo].[Order] ADD  CONSTRAINT [DF_Order_OrderStatus]  DEFAULT ((0)) FOR [OrderStatus]
GO
ALTER TABLE [dbo].[Order] ADD  CONSTRAINT [DF_Order_AuditStatus]  DEFAULT ((0)) FOR [AuditStatus]
GO
ALTER TABLE [dbo].[Order] ADD  CONSTRAINT [DF_Order_CreateTime]  DEFAULT (getdate()) FOR [CreateTime]
GO
ALTER TABLE [dbo].[Order] ADD  CONSTRAINT [DF_Order_AuditName]  DEFAULT ('') FOR [AuditName]
GO
ALTER TABLE [dbo].[Order] ADD  CONSTRAINT [DF_Order_CancelName]  DEFAULT ('') FOR [CancelName]
GO
ALTER TABLE [dbo].[Order] ADD  CONSTRAINT [DF_Order_CancelRemark]  DEFAULT ('') FOR [CancelRemark]
GO
