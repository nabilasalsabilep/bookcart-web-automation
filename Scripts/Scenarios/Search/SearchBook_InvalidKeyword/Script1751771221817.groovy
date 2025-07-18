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

WebUI.callTestCase(findTestCase('Blocks/Reusable_TC/OpenBrowser'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Header/HeaderWithoutLogin/menuLogin'))

WebUI.callTestCase(findTestCase('Blocks/Login/Login'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementVisible(findTestObject('Object Repository/Home/bookTitle'), 10)

GlobalVariable.BookTitle = bookTitle

WebUI.setText(findTestObject('Object Repository/Header/searchBox'), GlobalVariable.BookTitle)

boolean isBookFound = WebUI.verifyElementPresent(findTestObject('Object Repository/Header/recommendationBookTitleonSearch'), 5, FailureHandling.OPTIONAL)

if (isBookFound) {
	WebUI.comment("The book was found even though the keyword was invalid: " + GlobalVariable.BookTitle)
	WebUI.takeScreenshot()
} else {
	WebUI.comment("No search results as expected.")
}

WebUI.sendKeys(findTestObject('Object Repository/Header/searchBox'), Keys.chord(Keys.ENTER))

WebUI.delay(1)

WebUI.mouseOver(findTestObject('Object Repository/Home/bookImage'))

WebUI.verifyNotMatch(WebUI.getText(findTestObject('Object Repository/Home/bookTitleWhenHover')).trim(), GlobalVariable.BookTitle, 
    false)

WebUI.click(findTestObject('Object Repository/Home/bookImage'))

WebUI.verifyNotMatch(WebUI.getText(findTestObject('Object Repository/BookDetails/bookTitle')).trim(), GlobalVariable.BookTitle, 
    false)

WebUI.callTestCase(findTestCase('Blocks/Reusable_TC/CloseBrowser'), [:], FailureHandling.STOP_ON_FAILURE)