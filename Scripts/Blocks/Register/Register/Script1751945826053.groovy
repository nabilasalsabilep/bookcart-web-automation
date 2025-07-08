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

WebUI.click(findTestObject('Object Repository/Login/btnRegister'))

WebUI.delay(1)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Register/titleUserRegistration')), 'User Registration',
	false)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Register/titleAlreadyRegistered')), 'Already Registered?',
	false)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Register/btnLogin')), 'Login', false)

WebUI.setText(findTestObject('Object Repository/Register/inputFirstname'), 'User')

WebUI.setText(findTestObject('Object Repository/Register/inputLastname'), 'Tester')

RandomAccountGenerator newAccount = new RandomAccountGenerator()

GlobalVariable.Username = newAccount.randomUsername()

WebUI.setText(findTestObject('Object Repository/Register/inputUsername'), GlobalVariable.Username)

GlobalVariable.Password = newAccount.randomPassword()

WebUI.setText(findTestObject('Object Repository/Register/inputPassword'), GlobalVariable.Password)

WebUI.click(findTestObject('Object Repository/Register/iconPasswordVisibility'))

WebUI.setText(findTestObject('Object Repository/Register/inputConfirmPassword'), GlobalVariable.Password)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Register/labelGender')), 'Gender:', false)

WebUI.click(findTestObject('Object Repository/Register/radiobtnFemale'))

WebUI.click(findTestObject('Object Repository/Register/btnRegister'))

WebUI.delay(1)