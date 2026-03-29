import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.util.KeywordUtil

String BASE_URL   = 'http://localhost:3000'
String ADMIN_USER = 'admin@10yordle.com'
String ADMIN_PASS = 'Admin@123'
def loginAdmin() {
	WebUI.openBrowser('')
	WebUI.navigateToUrl(BASE_URL + '/login')
	WebUI.maximizeWindow()
	WebUI.waitForPageLoad(10)
	WebUI.setText(findTestObject('Page_EnglishMaster/input_ng nhp_email.rs'), ADMIN_USER)
	WebUI.setText(findTestObject('Page_EnglishMaster/input_ng nhp_password.rs'), ADMIN_PASS)
	WebUI.click(findTestObject('Page_EnglishMaster/button_ng nhp.rs'))
	WebUI.waitForPageLoad(10)
	KeywordUtil.logInfo('✔ Đăng nhập Admin thành công')
}

def goToQuanLyBaiTap() {
	WebUI.click(findTestObject('Page_CreateFlashcard/button_BT U.rs'))
	WebUI.waitForPageLoad(5)
	KeywordUtil.logInfo('✔ Đã vào trang Quản lý bài tập')
}

// Giao diện (UI): Kiểm tra hiển thị trang Quản lý bài học
KeywordUtil.logInfo('===== TC_01: Kiểm tra giao diện =====')
loginAdmin()
goToQuanLyBaiTap()
WebUI.verifyElementPresent(findTestObject('Page_CreateFlashcard/div_Flashcards.rs'), 10)
WebUI.verifyElementPresent(findTestObject('Page_CreateFlashcard/button_To flashcard.rs'), 10)
WebUI.verifyElementPresent(findTestObject('Page_CreateFlashcard/button_To mi.rs'), 10)
KeywordUtil.markPassed('TC_01 PASSED – Giao diện hiển thị đúng')

// Button "Tạo bài học mới": Kiểm tra điều hướng mở form
KeywordUtil.logInfo('===== TC_02: Button Tạo bài học mới =====')
WebUI.click(findTestObject('Page_CreateFlashcard/button_To flashcard.rs'))
WebUI.waitForPageLoad(5)
WebUI.verifyElementPresent(findTestObject('Page_CreateFlashcard/input_Mt khu.rs'), 10)
KeywordUtil.markPassed('TC_02 PASSED – Mở form Tạo mới thành công')

// Form Tạo mới: Tạo bài học thành công
// Data: Unit=Basics | Name=Lesson 1 | XP=10
KeywordUtil.logInfo('===== TC_03: Tạo bài học thành công =====')
WebUI.setText(findTestObject('Page_CreateFlashcard/input_Mt khu.rs'), 'Lesson 1')
WebUI.setText(findTestObject('Page_CreateFlashcard/textarea_VD_ accomplish.rs'), 'Basics')
WebUI.setText(findTestObject('Page_CreateFlashcard/textarea_VD_ hon thnh, t c.rs'), '10')
WebUI.click(findTestObject('Page_CreateFlashcard/button_To flashcard.rs'))
WebUI.waitForPageLoad(5)
WebUI.verifyElementPresent(findTestObject('Page_LearningGoals/msg_Toast.rs'), 10)
WebUI.verifyMatch(
	WebUI.getText(findTestObject('Page_LearningGoals/msg_Toast.rs')),
	'(?i).*th.*nh c.*ng.*', true)
KeywordUtil.markPassed('TC_03 PASSED – Tạo bài học thành công')

// Validate trường bắt buộc bị bỏ trống
KeywordUtil.logInfo('===== TC_04: Validate trường bắt buộc =====')
WebUI.click(findTestObject('Page_CreateFlashcard/button_To flashcard.rs'))
WebUI.waitForPageLoad(5)
WebUI.click(findTestObject('Page_CreateFlashcard/button_To flashcard.rs'))
WebUI.waitForPageLoad(3)
WebUI.verifyElementPresent(findTestObject('Page_LearningGoals/msg_Toast.rs'), 10)
WebUI.verifyMatch(
	WebUI.getText(findTestObject('Page_LearningGoals/msg_Toast.rs')),
	'(?i).*(tr.*ng|required|b.*t bu.*c).*', true)
KeywordUtil.markPassed('TC_04 PASSED – Validate trường bắt buộc đúng')

// Input XP thưởng: nhập ký tự không phải số
KeywordUtil.logInfo('===== TC_05: Validate XP thưởng =====')
WebUI.clearText(findTestObject('Page_CreateFlashcard/textarea_VD_ hon thnh, t c.rs'))
WebUI.setText(findTestObject('Page_CreateFlashcard/textarea_VD_ hon thnh, t c.rs'), 'abc')
WebUI.click(findTestObject('Page_CreateFlashcard/button_To flashcard.rs'))
WebUI.waitForPageLoad(3)
WebUI.verifyElementPresent(findTestObject('Page_LearningGoals/msg_Toast.rs'), 10)
WebUI.verifyMatch(
	WebUI.getText(findTestObject('Page_LearningGoals/msg_Toast.rs')),
	'(?i).*(s.*|number|kh.*ng h.*p l.*|XP).*', true)
KeywordUtil.markPassed('TC_05 PASSED – Validate XP không hợp lệ đúng')

// Button "Sửa": Form hiển thị đúng dữ liệu cũ
KeywordUtil.logInfo('===== TC_06: Button Sửa =====')
goToQuanLyBaiTap()
WebUI.click(findTestObject('Page_CreateFlashcard/button_To mi.rs'))
WebUI.waitForPageLoad(5)
WebUI.verifyElementPresent(findTestObject('Page_CreateFlashcard/input_Mt khu.rs'), 10)
WebUI.verifyNotEqual(
	WebUI.getAttribute(findTestObject('Page_CreateFlashcard/input_Mt khu.rs'), 'value'),
	'', 'Form Sửa phải điền sẵn tên bài học')
KeywordUtil.markPassed('TC_06 PASSED – Form Sửa hiển thị đúng dữ liệu cũ')

// Button "Cập nhật": Lưu thay đổi thành công
// Data: Name = "Lesson 3 Updated"
KeywordUtil.logInfo('===== TC_07: Button Cập nhật =====')
WebUI.clearText(findTestObject('Page_CreateFlashcard/input_Mt khu.rs'))
WebUI.setText(findTestObject('Page_CreateFlashcard/input_Mt khu.rs'), 'Lesson 3 Updated')
WebUI.click(findTestObject('Page_CreateFlashcard/button_ng nhp.rs'))
WebUI.waitForPageLoad(5)
WebUI.verifyElementPresent(findTestObject('Page_LearningGoals/msg_Toast.rs'), 10)
WebUI.verifyMatch(
	WebUI.getText(findTestObject('Page_LearningGoals/msg_Toast.rs')),
	'(?i).*th.*nh c.*ng.*', true)
WebUI.verifyElementPresent(findTestObject('Page_CreateFlashcard/div_Flashcards.rs'), 10)
KeywordUtil.markPassed('TC_07 PASSED – Cập nhật bài học thành công')

// Button "Ẩn/Hiện": Đổi trạng thái thành công
KeywordUtil.logInfo('===== TC_08: Button Ẩn/Hiện =====')
goToQuanLyBaiTap()
String statusBefore = WebUI.getText(findTestObject('Page_CreateFlashcard/button_To mi.rs'))
WebUI.click(findTestObject('Page_CreateFlashcard/button_ng nhp.rs'))
WebUI.waitForPageLoad(3)
String statusAfter = WebUI.getText(findTestObject('Page_CreateFlashcard/button_To mi.rs'))
WebUI.verifyNotEqual(statusBefore, statusAfter, 'Trạng thái phải thay đổi')
KeywordUtil.markPassed('TC_08 PASSED – Đổi trạng thái Ẩn/Hiện thành công')

// Button "Xóa": Xóa bài học khỏi danh sách
KeywordUtil.logInfo('===== TC_09: Button Xóa =====')
goToQuanLyBaiTap()
WebUI.click(findTestObject('Page_CreateFlashcard/button_BT U.rs'))
WebUI.waitForElementPresent(findTestObject('Page_LearningGoals/msg_Toast.rs'), 5)
try {
	WebUI.acceptAlert()
} catch (Exception e) {
	// Không có confirm dialog → bỏ qua
}
WebUI.waitForPageLoad(5)
WebUI.verifyElementNotPresent(findTestObject('Page_CreateFlashcard/div_Flashcards.rs'), 5)
KeywordUtil.markPassed('TC_09 PASSED – Xóa bài học thành công')

// Button "Hủy": Dữ liệu không được lưu
KeywordUtil.logInfo('===== TC_10: Button Hủy =====')
WebUI.click(findTestObject('Page_CreateFlashcard/button_To flashcard.rs'))
WebUI.waitForPageLoad(5)
WebUI.setText(findTestObject('Page_CreateFlashcard/input_Mt khu.rs'), 'Bài học sẽ bị hủy')
WebUI.click(findTestObject('Page_LearningGoals/btn_Cancel.rs'))
WebUI.waitForPageLoad(3)
WebUI.verifyElementPresent(findTestObject('Page_CreateFlashcard/div_Flashcards.rs'), 10)
WebUI.verifyElementNotPresent(findTestObject('Page_CreateFlashcard/input_Mt khu.rs'), 5)
KeywordUtil.markPassed('TC_10 PASSED – Hủy thao tác, không lưu dữ liệu')

WebUI.closeBrowser()
KeywordUtil.logInfo('===== Hoàn thành TC_01 → TC_10 Quản lý bài tập =====')