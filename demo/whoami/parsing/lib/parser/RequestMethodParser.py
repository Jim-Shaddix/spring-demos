from typing import List, Any

from lib.parser.AbstractParser import AbstractParser
from lib.util.WebDriverUtil import next_element
from lib.util.WebDriverUtil import css_select
from lib.util.WebDriverUtil import css_select_all
from lib.config.config import Config
from lib.model.RequestMethodSpec import RequestMethodSpec

from selenium.webdriver.chrome.webdriver import WebDriver


class RequestMethodParser(AbstractParser):

    parsed_file_location = Config.REQUEST_METHOD_FILE

    @classmethod
    def _parse_content(cls, driver: WebDriver) -> List[Any]:

        dl_element = css_select(driver, "dl")
        dt_elements = css_select_all(dl_element, "dt")

        request_method_list = list()

        for dt_elem in dt_elements:

            a_elem = css_select(dt_elem, "a")
            code_elem = css_select(a_elem, "code")
            dd_elem = next_element(driver, dt_elem)
            p_elem = css_select(dd_elem, "p")

            link = a_elem.get_property("href")
            name = code_elem.text
            desc = p_elem.text

            request_method = RequestMethodSpec(name, desc, link)

            print(request_method)

            request_method_list.append(request_method)

        return request_method_list
