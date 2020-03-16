function search(currentPage) {
	$("#currentPage").val(currentPage);
	$("#mainForm").attr("method","GET");
	$("#mainForm").attr("action",$("#basePath").val() + "/comments");
	$("#mainForm").submit();
}