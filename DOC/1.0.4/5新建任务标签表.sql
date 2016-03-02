USE [RenRenDB]
GO
--DROP TABLE TaskTag
/****** Object:  Table [dbo].[TaskTag]    Script Date: 02/16/2016 11:47:58 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[TaskTag](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[TagName] [nvarchar](50) NOT NULL,
	[TagColorCode] [nvarchar](50) NOT NULL,
	[TagStatus] [int] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[CreateName] [nvarchar](50) NOT NULL,
	[UpdateTime] [datetime] NOT NULL,
	[UpdateName] [nvarchar](50) NOT NULL
 CONSTRAINT [PK_TaskTag] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'自增的主键' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'TaskTag', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'标签名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'TaskTag', @level2type=N'COLUMN',@level2name=N'TagName'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'标签颜色编码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'TaskTag', @level2type=N'COLUMN',@level2name=N'TagColorCode'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'TaskTag', @level2type=N'COLUMN',@level2name=N'CreateTime'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'创建人' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'TaskTag', @level2type=N'COLUMN',@level2name=N'CreateName'
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'更新时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'TaskTag', @level2type=N'COLUMN',@level2name=N'UpdateTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'修改人' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'TaskTag', @level2type=N'COLUMN',@level2name=N'UpdateName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'标签状态，默认1有效（备用）' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'TaskTag', @level2type=N'COLUMN',@level2name=N'TagStatus'
GO

ALTER TABLE [dbo].[TaskTag] ADD  CONSTRAINT [DF_TaskTag_CreateTime]  DEFAULT (getdate()) FOR [CreateTime]
GO

ALTER TABLE [dbo].[TaskTag] ADD  CONSTRAINT [DF_TaskTag_UpdateTime]  DEFAULT (getdate()) FOR [UpdateTime]
GO
ALTER TABLE [dbo].[TaskTag] ADD  CONSTRAINT [DF_TaskTag_TagStatus]  DEFAULT (1) FOR [TagStatus]
GO

