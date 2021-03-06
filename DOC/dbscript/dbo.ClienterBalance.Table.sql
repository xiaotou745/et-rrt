USE [RenRenDB]
GO
/****** Object:  Table [dbo].[ClienterBalance]    Script Date: 09/25/2015 15:38:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ClienterBalance](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[ClienterId] [bigint] NOT NULL,
	[Balance] [decimal](18, 2) NOT NULL,
	[Withdraw] [decimal](18, 2) NOT NULL,
	[HadWithdraw] [decimal](18, 2) NOT NULL,
 CONSTRAINT [PK_ClienterBalance] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'C端用户ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ClienterBalance', @level2type=N'COLUMN',@level2name=N'ClienterId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'余额' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ClienterBalance', @level2type=N'COLUMN',@level2name=N'Balance'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'可提现' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ClienterBalance', @level2type=N'COLUMN',@level2name=N'Withdraw'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'已提现' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'ClienterBalance', @level2type=N'COLUMN',@level2name=N'HadWithdraw'
GO
ALTER TABLE [dbo].[ClienterBalance] ADD  CONSTRAINT [DF_ClienterBalance_BalancePrice]  DEFAULT ((0)) FOR [Balance]
GO
ALTER TABLE [dbo].[ClienterBalance] ADD  CONSTRAINT [DF_ClienterBalance_Withdraw]  DEFAULT ((0)) FOR [Withdraw]
GO
ALTER TABLE [dbo].[ClienterBalance] ADD  CONSTRAINT [DF_ClienterBalance_Cumulative]  DEFAULT ((0)) FOR [HadWithdraw]
GO
