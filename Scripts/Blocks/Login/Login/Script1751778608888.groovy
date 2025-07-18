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

WebUI.delay(2)

WebUI.waitForElementVisible(findTestObject('Object Repository/Login/titleLogin'), 10)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Login/titleLogin')).trim(), 'Login', false)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Login/titleNewUser')).trim(), 'New User?', false)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Login/btnRegister')).trim(), 'Register', false)

WebUI.setText(findTestObject('Object Repository/Login/inputUsername'), GlobalVariable.Username)

WebUI.setText(findTestObject('Object Repository/Login/inputPassword'), GlobalVariable.Password)

WebUI.click(findTestObject('Object Repository/Login/btnLogin'))

WebUI.delay(3)

if (WebUI.getUrl().equals('https://bookcart.azurewebsites.net/')) {
	
	WebUI.comment("Login success - home page appears")
	
	WebUI.waitForElementVisible(findTestObject('Object Repository/Header/HeaderWithLogin/accountUsername'), 10)
	
	WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Header/HeaderWithLogin/accountUsername')).trim(), GlobalVariable.Username, false)
	
} else {
	WebUI.comment("Login failed despite valid data")
	
	WebUI.takeScreenshot()
	
	WebUI.callTestCase(findTestCase('Blocks/Reusable_TC/CloseBrowser'), [:], FailureHandling.STOP_ON_FAILURE)
}