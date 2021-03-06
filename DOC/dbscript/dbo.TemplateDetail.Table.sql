USE [RenRenDB]
GO
/****** Object:  Table [dbo].[TemplateDetail]    Script Date: 09/25/2015 15:38:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TemplateDetail](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[ControlId] [bigint] NOT NULL,
	[OrderNum] [int] NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[Title] [nvarchar](50) NOT NULL,
	[DefaultValue] [nvarchar](500) NOT NULL,
	[ControlData] [nvarchar](500) NOT NULL,
 CONSTRAINT [PK_TemplateDetail] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'控件ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'TemplateDetail', @level2type=N'COLUMN',@level2name=N'ControlId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'排序' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'TemplateDetail', @level2type=N'COLUMN',@level2name=N'OrderNum'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'控件name属性值' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'TemplateDetail', @level2type=N'COLUMN',@level2name=N'Name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'展示名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'TemplateDetail', @level2type=N'COLUMN',@level2name=N'Title'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'默认值' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'TemplateDetail', @level2type=N'COLUMN',@level2name=N'DefaultValue'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'控件值' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'TemplateDetail', @level2type=N'COLUMN',@level2name=N'ControlData'
GO
ALTER TABLE [dbo].[TemplateDetail] ADD  CONSTRAINT [DF_TemplateDetail_OrderNum]  DEFAULT ((0)) FOR [OrderNum]
GO
ALTER TABLE [dbo].[TemplateDetail] ADD  CONSTRAINT [DF_TemplateDetail_Name]  DEFAULT ('') FOR [Name]
GO
ALTER TABLE [dbo].[TemplateDetail] ADD  CONSTRAINT [DF_TemplateDetail_Title]  DEFAULT ('') FOR [Title]
GO
ALTER TABLE [dbo].[TemplateDetail] ADD  CONSTRAINT [DF_TemplateDetail_DefaultValue]  DEFAULT ('') FOR [DefaultValue]
GO
ALTER TABLE [dbo].[TemplateDetail] ADD  CONSTRAINT [DF_TemplateDetail_ValueData]  DEFAULT ('') FOR [ControlData]
GO
