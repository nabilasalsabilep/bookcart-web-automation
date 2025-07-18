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

WebUI.waitForElementVisible(findTestObject('Object Repository/Login/titleLogin'), 10)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Login/titleLogin')), 'Login', false)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Login/titleNewUser')), 'New User?', false)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Login/btnRegister')), 'Register', false)

GlobalVariable.Username = username

GlobalVariable.Password = password

WebUI.setText(findTestObject('Object Repository/Login/inputUsername'), GlobalVariable.Username)

WebUI.setText(findTestObject('Object Repository/Login/inputPassword'), GlobalVariable.Password)

WebUI.click(findTestObject('Object Repository/Login/btnLogin'))

currentUrl = WebUI.getUrl()
if (currentUrl.contains("/login")) {
	
	WebUI.comment("Login failed accordingly: the user remains on the login page due to an incorrect password.")
	
	WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Login/titleLogin')), 'Login', false)
	
	WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Login/titleNewUser')), 'New User?', false)
	
	WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Login/btnRegister')), 'Register', false)
	
	
} else {
	
	WebUI.comment("Login succeeds when it should fail.")
	
	WebUI.takeScreenshot()
	
}

WebUI.callTestCase(findTestCase('Blocks/Reusable_TC/CloseBrowser'), [:], FailureHandling.STOP_ON_FAILURE)