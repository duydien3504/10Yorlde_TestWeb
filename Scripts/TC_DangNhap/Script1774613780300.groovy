import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling

/**
 * Test Case ID: TC001
 * Description: Kiểm tra đăng nhập thành công với tài khoản email/mật khẩu hợp lệ
 * PreCondition: Tài khoản đã tồn tại và đã kích hoạt
 */

// 1. Mở trình duyệt và điều hướng đến trang chủ
WebUI.openBrowser('')
WebUI.maximizeWindow()
WebUI.navigateToUrl('http://localhost:3000/')

// Đường dẫn cơ sở đến các Object trong Repository của bạn
String loginRepo = 'Object Repository/Page_CreateFlashcard/Page_EnglishMaster - Nng cao k nng ting Anh ca bn/'

// 2. Nhấn nút "TÔI ĐÃ CÓ TÀI KHOẢN" (Nếu có bước này trong quy trình của bạn)
if (WebUI.waitForElementVisible(findTestObject('Object Repository/Page_EnglishMaster - Nng cao k nng ting Anh ca bn/button_TI  C TI KHON'), 5, FailureHandling.OPTIONAL)) {
    WebUI.click(findTestObject('Object Repository/Page_EnglishMaster - Nng cao k nng ting Anh ca bn/button_TI  C TI KHON'))
}

// 3. Nhập Email hợp lệ
// Sử dụng Test Data từ file Excel: testuser@example.com
WebUI.setText(findTestObject(loginRepo + 'input_Email hoc tn ng nhp'), 'testuser@example.com')

// 4. Nhập mật khẩu đúng
// Lưu ý: Trong thực tế bạn nên dùng setEncryptedText cho bảo mật
WebUI.setText(findTestObject(loginRepo + 'input_Mt khu'), '12345678')

// 5. Nhấn nút "Đăng nhập"
WebUI.click(findTestObject(loginRepo + 'button_ng nhp'))

// 6. Kiểm tra kết quả (Expected Output)
// Kiểm tra hiển thị Toast "Đăng nhập thành công!"
String toastRepo = 'Object Repository/Page_LearningGoals/msg_Toast'
if (WebUI.waitForElementVisible(findTestObject(toastRepo), 5)) {
    String message = WebUI.getText(findTestObject(toastRepo))
    if (message.contains('Đăng nhập thành công')) {
        println('XÁC NHẬN: Hiển thị Toast thông báo thành công.')
    }
}

// Kiểm tra điều hướng về trang /learn
String currentUrl = WebUI.getUrl()
if (currentUrl.contains('/learn')) {
    println('XÁC NHẬN: Đã điều hướng về trang học tập (/learn).')
}

WebUI.delay(2)
WebUI.closeBrowser()
