$(function() {
	common.showMessage($("#message").val());
});

function search(currentPage) {
	$("#currentPage").val(currentPage);
	$("#mainForm").submit();
}