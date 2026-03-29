import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('')

WebUI.navigateToUrl('http://localhost:3000/')

WebUI.click(findTestObject('Page_CreateFlashcard/Page_EnglishMaster - Nng cao k nng ting Anh ca bn/button_BT U'))

WebUI.setText(findTestObject('Page_CreateFlashcard/Page_EnglishMaster - Nng cao k nng ting Anh ca bn/input_Email hoc tn ng nhp'), 
    'sonnguyen17072004@gmail.com')

WebUI.setEncryptedText(findTestObject('Page_CreateFlashcard/Page_EnglishMaster - Nng cao k nng ting Anh ca bn/input_Mt khu'), 
    'CSt51UwWECc=')

WebUI.click(findTestObject('Page_CreateFlashcard/Page_EnglishMaster - Nng cao k nng ting Anh ca bn/button_ng nhp'))

WebUI.click(findTestObject('Page_CreateFlashcard/Page_EnglishMaster - Nng cao k nng ting Anh ca bn/div_Flashcards'))

WebUI.click(findTestObject('Page_CreateFlashcard/Page_EnglishMaster - Nng cao k nng ting Anh ca bn/button_To flashcard'))

WebUI.selectOptionByValue(findTestObject('Page_CreateFlashcard/Page_EnglishMaster - Nng cao k nng ting Anh ca bn/select_deck'), 
    iddeck, false)

WebUI.setText(findTestObject('Page_CreateFlashcard/Page_EnglishMaster - Nng cao k nng ting Anh ca bn/textarea_VD_ accomplish'), 
    front)

WebUI.setText(findTestObject('Page_CreateFlashcard/Page_EnglishMaster - Nng cao k nng ting Anh ca bn/textarea_VD_ hon thnh, t c'), 
    back)

WebUI.setText(findTestObject('Page_CreateFlashcard/Page_EnglishMaster - Nng cao k nng ting Anh ca bn/textarea_VD_ She accomplished all her goals this'), 
   example)

WebUI.click(findTestObject('Page_CreateFlashcard/Page_EnglishMaster - Nng cao k nng ting Anh ca bn/button_To mi'))

