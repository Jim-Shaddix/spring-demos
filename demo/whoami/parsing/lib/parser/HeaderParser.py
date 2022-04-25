from typing import List, Any

from lib.parser.AbstractParser import AbstractParser
from lib.util.WebDriverUtil import next_element
from lib.config.config import Config

from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.webdriver import WebDriver
from lib.model.HeaderSpec import HeaderDesc


class HeaderParser(AbstractParser):

    parsed_file_location = Config.HEADER_SPEC_FILE

    @classmethod
    def _parse_content(cls, driver: WebDriver) -> List[Any]:

        header_list = list()

        # Parse the results of the search
        header_type_elems = driver.find_elements(by=By.CSS_SELECTOR, value="h2")[2:-6] + driver.find_elements(by=By.TAG_NAME, value="h3")

        for header_type_elem in header_type_elems:

            header_type = header_type_elem.text
            div_after_header_type_elem = next_element(driver, header_type_elem)
            header_link_elems = div_after_header_type_elem.find_elements(By.CSS_SELECTOR, "dt a")
            header_desc_elems = div_after_header_type_elem.find_elements(By.CSS_SELECTOR, "dd p")

            for link_elem, desc_elem in zip(header_link_elems, header_desc_elems):

                name = link_elem.text
                desc = desc_elem.text
                link = link_elem.get_attribute("href")
                header_desc = HeaderDesc(header_type, name, desc, link)
                print(header_desc)
                header_list.append(header_desc)

        return header_list
