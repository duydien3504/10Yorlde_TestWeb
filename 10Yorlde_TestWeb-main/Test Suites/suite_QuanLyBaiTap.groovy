import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.model.FailureHandling

KeywordUtil.logInfo('========================================')
KeywordUtil.logInfo('  BẮT ĐẦU SUITE: Quản lý bài tập Admin ')
KeywordUtil.logInfo('========================================')

// ---------- Danh sách Test Case theo thứ tự ----------
def testCases = [
	[id: 'TC_01', name: 'Giao diện (UI)',              path: 'Test Cases/TC_QuanLyBaiTap'],
	[id: 'TC_02', name: 'Button Tạo bài học mới',      path: 'Test Cases/TC_QuanLyBaiTap'],
	[id: 'TC_03', name: 'Form Tạo mới thành công',     path: 'Test Cases/TC_QuanLyBaiTap'],
	[id: 'TC_04', name: 'Validate trường bắt buộc',    path: 'Test Cases/TC_QuanLyBaiTap'],
	[id: 'TC_05', name: 'Validate XP thưởng',          path: 'Test Cases/TC_QuanLyBaiTap'],
	[id: 'TC_06', name: 'Button Sửa',                  path: 'Test Cases/TC_QuanLyBaiTap'],
	[id: 'TC_07', name: 'Button Cập nhật',             path: 'Test Cases/TC_QuanLyBaiTap'],
	[id: 'TC_08', name: 'Button Ẩn/Hiện',              path: 'Test Cases/TC_QuanLyBaiTap'],
	[id: 'TC_09', name: 'Button Xóa',                  path: 'Test Cases/TC_QuanLyBaiTap'],
	[id: 'TC_10', name: 'Button Hủy',                  path: 'Test Cases/TC_QuanLyBaiTap'],
]

// ---------- Biến đếm kết quả ----------
int totalPass   = 0
int totalFail   = 0
int totalSkip   = 0
List<String> failedList = []

// ---------- Chạy từng Test Case ----------
testCases.each { tc ->
	KeywordUtil.logInfo("---------- Chạy ${tc.id}: ${tc.name} ----------")
	try {
		WebUI.callTestCase(findTestCase(tc.path), [:], FailureHandling.CONTINUE_ON_FAILURE)
		totalPass++
		KeywordUtil.logInfo("✔ ${tc.id} – PASSED")
	} catch (Exception e) {
		totalFail++
		failedList << "${tc.id}: ${tc.name}"
		KeywordUtil.logInfo("✘ ${tc.id} – FAILED: ${e.message}")
	}
}

// ---------- In báo cáo tổng kết ----------
KeywordUtil.logInfo('========================================')
KeywordUtil.logInfo("  KẾT QUẢ SUITE: Quản lý bài tập Admin")
KeywordUtil.logInfo("  Tổng TC   : ${testCases.size()}")
KeywordUtil.logInfo("  ✔ PASSED  : ${totalPass}")
KeywordUtil.logInfo("  ✘ FAILED  : ${totalFail}")
if (failedList) {
	KeywordUtil.logInfo("  Danh sách FAIL:")
	failedList.each { KeywordUtil.logInfo("    – ${it}") }
}
KeywordUtil.logInfo('========================================')

// ---------- Đánh dấu Suite PASS/FAIL ----------
if (totalFail > 0) {
	KeywordUtil.markFailed("Suite FAILED – ${totalFail}/${testCases.size()} test case thất bại")
} else {
	KeywordUtil.markPassed("Suite PASSED – Tất cả ${totalPass}/${testCases.size()} test case thành công")
}