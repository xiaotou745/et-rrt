USE [RenRenDB]
GO
/****** Object:  Table [dbo].[BusinessBalanceRecord]    Script Date: 09/25/2015 15:38:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[BusinessBalanceRecord](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[BusinessId] [bigint] NOT NULL,
	[Amount] [decimal](18, 2) NOT NULL,
	[AfterAmount] [decimal](18, 2) NOT NULL,
	[RecordType] [smallint] NOT NULL,
	[OptName] [nvarchar](50) NOT NULL,
	[OperateTime] [datetime] NOT NULL,
	[OrderId] [bigint] NOT NULL,
	[RelationNo] [varchar](50) NOT NULL,
	[Remark] [nvarchar](max) NOT NULL,
 CONSTRAINT [PK_BusinessBalanceRecord] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'骑士ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BusinessBalanceRecord', @level2type=N'COLUMN',@level2name=N'BusinessId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'金额' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BusinessBalanceRecord', @level2type=N'COLUMN',@level2name=N'Amount'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'交易后金额' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BusinessBalanceRecord', @level2type=N'COLUMN',@level2name=N'AfterAmount'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'交易类型(1：发布任务 2充值)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BusinessBalanceRecord', @level2type=N'COLUMN',@level2name=N'RecordType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'操作人' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BusinessBalanceRecord', @level2type=N'COLUMN',@level2name=N'OptName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'操作时间 ' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BusinessBalanceRecord', @level2type=N'COLUMN',@level2name=N'OperateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'关联号ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BusinessBalanceRecord', @level2type=N'COLUMN',@level2name=N'OrderId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'关联号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BusinessBalanceRecord', @level2type=N'COLUMN',@level2name=N'RelationNo'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'备注' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BusinessBalanceRecord', @level2type=N'COLUMN',@level2name=N'Remark'
GO
ALTER TABLE [dbo].[BusinessBalanceRecord] ADD  CONSTRAINT [DF_BusinessBalanceRecord_Amount]  DEFAULT ((0)) FOR [Amount]
GO
ALTER TABLE [dbo].[BusinessBalanceRecord] ADD  CONSTRAINT [DF_BusinessBalanceRecord_AfterAmount]  DEFAULT ((0)) FOR [AfterAmount]
GO
ALTER TABLE [dbo].[BusinessBalanceRecord] ADD  CONSTRAINT [DF_BusinessBalanceRecord_OptName]  DEFAULT ('') FOR [OptName]
GO
ALTER TABLE [dbo].[BusinessBalanceRecord] ADD  CONSTRAINT [DF_BusinessBalanceRecord_OperateTime]  DEFAULT (getdate()) FOR [OperateTime]
GO
ALTER TABLE [dbo].[BusinessBalanceRecord] ADD  CONSTRAINT [DF_BusinessBalanceRecord_RelationNo]  DEFAULT ('') FOR [RelationNo]
GO
