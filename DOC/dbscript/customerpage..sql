USE [RenRenDB]
GO

/****** Object:  StoredProcedure [dbo].[Sp_CustomPage2015_V1]    Script Date: 09/28/2015 11:18:55 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO




CREATE PROC [dbo].[Sp_CustomPage2015_V1]
--SqlServer2008  �кŷ�ҳ  V1.2   ����ȫ�Ĳ�ѯ����������ֹע��

@OrderByColumn varchar(8000),--�����ֶ���
@ColumnList varchar(max),--���ص��ֶ��б�
@TableList varchar(8000),--����������ŷָ�
@Condition varchar(8000),--��ѯ����

@PageSize int,
@CurrentPage int,
@IsAccount bit,
@TotalRecord int output,
@TotalPage int output
AS
set @TotalRecord=0;
set @TotalPage=0;
--�����¼��
IF @IsAccount = 1
BEGIN
	DECLARE @temp2 NVARCHAR(2000)
	--��������ҳ�����ļ������
	set @temp2=N'select @total2=count(1) from ' + @TableList + ' where ' + @Condition
	--ִ�м������,ȡ���ܵļ�¼����
	PRINT (@temp2)
		exec sp_executesql @temp2,N' @total2 int output ',@TotalRecord output
	IF @TotalRecord > 0
	BEGIN
		set @TotalPage = (@TotalRecord+@PageSize-1)/@PageSize
	END
END
ELSE
BEGIN
	SET @TotalRecord = 0
	SET @TotalPage = 0
END

DECLARE @StartRowNum INT
SET @StartRowNum = @PageSize*(@CurrentPage-1) + 1
DECLARE @EndRowNum INT
SET @EndRowNum = @StartRowNum + @PageSize - 1
DECLARE @sql NVARCHAR(max)
SET @sql = N'SELECT * FROM ( SELECT ' + @ColumnList + ',ROW_NUMBER() OVER (order by ' + @OrderByColumn + ' ) AS RowNum FROM ' + @TableList +' WHERE ' + @Condition + ' ) AS T WHERE RowNum BETWEEN ' + CAST(@StartRowNum AS VARCHAR(50)) + ' AND ' + CAST(@EndRowNum AS VARCHAR(50))

PRINT @sql

	exec sp_executesql @sql


GO


