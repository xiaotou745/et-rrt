USE [RenRenDB]
GO
/****** Object:  Table [dbo].[BusinessRecharge]    Script Date: 09/25/2015 15:38:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[BusinessRecharge](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[BusinessId] [bigint] NOT NULL,
	[PayType] [int] NOT NULL,
	[OrderNo] [varchar](100) NOT NULL,
	[PayAmount] [decimal](18, 2) NOT NULL,
	[PayStatus] [int] NOT NULL,
	[PayBy] [nvarchar](100) NOT NULL,
	[PayTime] [datetime] NOT NULL,
	[OriginalOrderNo] [varchar](100) NOT NULL,
 CONSTRAINT [PK_BusinessRecharge] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'商家ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BusinessRecharge', @level2type=N'COLUMN',@level2name=N'BusinessId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'支付方式：1：支付宝；2微信;3后台;4赠送' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BusinessRecharge', @level2type=N'COLUMN',@level2name=N'PayType'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'站内订单号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BusinessRecharge', @level2type=N'COLUMN',@level2name=N'OrderNo'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'支付金额，必须大于0.01元' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BusinessRecharge', @level2type=N'COLUMN',@level2name=N'PayAmount'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'子订单状态:默认已支付(0待支付 ,1 已支付)' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BusinessRecharge', @level2type=N'COLUMN',@level2name=N'PayStatus'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'支付人' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BusinessRecharge', @level2type=N'COLUMN',@level2name=N'PayBy'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'支付时间：默认getdate()' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BusinessRecharge', @level2type=N'COLUMN',@level2name=N'PayTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'第三方平台订单号' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BusinessRecharge', @level2type=N'COLUMN',@level2name=N'OriginalOrderNo'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'商家充值表' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BusinessRecharge'
GO
ALTER TABLE [dbo].[BusinessRecharge] ADD  CONSTRAINT [DF_BusinessRecharge_PayType]  DEFAULT ((3)) FOR [PayType]
GO
ALTER TABLE [dbo].[BusinessRecharge] ADD  CONSTRAINT [DF_BusinessRecharge_PayStatus]  DEFAULT ((0)) FOR [PayStatus]
GO
ALTER TABLE [dbo].[BusinessRecharge] ADD  CONSTRAINT [DF_BusinessRecharge_PayTime]  DEFAULT (getdate()) FOR [PayTime]
GO
