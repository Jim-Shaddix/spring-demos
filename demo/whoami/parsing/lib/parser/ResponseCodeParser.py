from typing import List, Any

from lib.parser.AbstractParser import AbstractParser
from lib.util.WebDriverUtil import next_element, css_select, css_select_all
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

            code_type = css_select(sec_elem, "h2 a").text
            print(f" --- parsing section: {code_type}")

            for list_elem in css_select_all(sec_elem, "div dl dt"):

                try:
                    a_element = css_select(list_elem, "a")
                    code_element = css_select(a_element, "code")
                    link = a_element.get_property("href")
                except NoSuchElementException as e:
                    code_element = list_elem.find_element(by=By.CSS_SELECTOR, value="code")
                    link = "null"

                dd_elem = next_element(driver, list_elem)
                p_elem = css_select(dd_elem, "p")

                desc = p_elem.text
                name = code_element.text
                res = ResponseCodeSpec(code_type, name, desc, link)
                response_code_list.append(res)
                print(res)

        return response_code_list
