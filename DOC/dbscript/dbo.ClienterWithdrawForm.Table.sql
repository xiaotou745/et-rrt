USE [RenRenDB]
GO
/****** Object:  Table [dbo].[ClienterWithdrawForm]    Script Date: 09/25/2015 15:38:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ClienterWithdrawForm](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[ClienterId] [bigint] NOT NULL,
	[Amount] [decimal](18, 2) NOT NULL,
	[WithdrawNo] [varchar](50) NOT NULL,
	[WithType] [smallint] NOT NULL,
	[AccountInfo] [varchar](255) NOT NULL,
	[TrueName] [nvarchar](50) NOT NULL,
	[Status] [smallint] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[AuditTime] [datetime] NULL,
	[AuditName] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_ClienterWithdrawForm] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'提现金额' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ClienterWithdrawForm', @level2type=N'COLUMN',@level2name=N'Amount'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'提现单号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ClienterWithdrawForm', @level2type=N'COLUMN',@level2name=N'WithdrawNo'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'默认1支付宝' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ClienterWithdrawForm', @level2type=N'COLUMN',@level2name=N'WithType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'提现账号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ClienterWithdrawForm', @level2type=N'COLUMN',@level2name=N'AccountInfo'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'真实姓名' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ClienterWithdrawForm', @level2type=N'COLUMN',@level2name=N'TrueName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'1审核通过，2审核拒绝，默认0待审核' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ClienterWithdrawForm', @level2type=N'COLUMN',@level2name=N'Status'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'审核时间 ' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ClienterWithdrawForm', @level2type=N'COLUMN',@level2name=N'AuditTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'审核人' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ClienterWithdrawForm', @level2type=N'COLUMN',@level2name=N'AuditName'
GO
ALTER TABLE [dbo].[ClienterWithdrawForm] ADD  CONSTRAINT [DF_ClienterWithdrawForm_Amount]  DEFAULT ((0)) FOR [Amount]
GO
ALTER TABLE [dbo].[ClienterWithdrawForm] ADD  CONSTRAINT [DF_ClienterWithdrawForm_WithType]  DEFAULT ((1)) FOR [WithType]
GO
ALTER TABLE [dbo].[ClienterWithdrawForm] ADD  CONSTRAINT [DF_ClienterWithdrawForm_TrueName]  DEFAULT ('') FOR [TrueName]
GO
ALTER TABLE [dbo].[ClienterWithdrawForm] ADD  CONSTRAINT [DF_ClienterWithdrawForm_CreateTime]  DEFAULT (getdate()) FOR [CreateTime]
GO
ALTER TABLE [dbo].[ClienterWithdrawForm] ADD  CONSTRAINT [DF_ClienterWithdrawForm_AuditName]  DEFAULT ('') FOR [AuditName]
GO
