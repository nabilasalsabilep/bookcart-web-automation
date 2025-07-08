import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class RandomAccountGenerator {

	@Keyword
	def randomUsername() {
		def randomNumber = (10000..99999).toList().get(new Random().nextInt(90000))
		def randomUsername = "usertest${randomNumber}_"
		return randomUsername
	}

	@Keyword
	def randomPassword() {
		String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
		String lower = "abcdefghijklmnopqrstuvwxyz"
		String digits = "0123456789"
		String allChars = upper + lower + digits

		Random random = new Random()
		StringBuilder password = new StringBuilder()

		password.append(upper.charAt(random.nextInt(upper.length())))
		password.append(lower.charAt(random.nextInt(lower.length())))
		password.append(digits.charAt(random.nextInt(digits.length())))

		for (int i = 0; i < 5; i++) {
			password.append(allChars.charAt(random.nextInt(allChars.length())))
		}

		return password.toList().sort { random.nextInt() }.join()
	}
}
