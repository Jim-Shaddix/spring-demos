from typing import List, Any

from lib.parser.AbstractParser import AbstractParser
from lib.util.WebDriverUtil import next_element
from lib.model.ResponseCodeSpec import ResponseCodeSpec
from lib.config.config import Config

from selenium.common.exceptions import NoSuchElementException
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.webdriver import WebDriver


class ResponseCodeParser(AbstractParser):

    parsed_file_location = Config.RETURN_CODE_FILE

    @classmethod
    def _parse_content(cls, driver: WebDriver) -> List[Any]:

        section_elements = driver.find_elements(by=By.CSS_SELECTOR, value="section")[2:7]

        response_code_list = list()

        for sec_elem in section_elements:
            type = sec_elem.find_element(by=By.CSS_SELECTOR, value="h2 a").text
            print(f" --- parsing section: {type}")
            section_contents = sec_elem.find_elements(by=By.CSS_SELECTOR, value="div dl dt")
            for list_elem in section_contents:
                try:
                    a_element = list_elem.find_element(by=By.CSS_SELECTOR, value="a")
                    code_element = a_element.find_element(by=By.CSS_SELECTOR, value="code")
                    link = a_element.get_property("href")
                except NoSuchElementException as e:
                    code_element = a_element.find_element(by=By.CSS_SELECTOR, value="code")
                    link = "null"

                dd_elem = next_element(driver, list_elem)
                desc = dd_elem.find_element(by=By.CSS_SELECTOR, value="p").text
                name = code_element.text
                res = ResponseCodeSpec(type, name, desc, link)
                response_code_list.append(res)
                print(res)

        return response_code_list
