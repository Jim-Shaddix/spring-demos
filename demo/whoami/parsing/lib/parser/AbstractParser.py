import json
import sys
from abc import ABC, abstractmethod
from typing import Any, List
from selenium.webdriver.chrome.webdriver import WebDriver
from lib.config.config import Config
from pathlib import Path


class AbstractParser(ABC):

    parsed_file_location = ""

    @classmethod
    @abstractmethod
    def _parse_content(cls, driver: WebDriver) -> List[Any]:
        pass

    @classmethod
    def _wright_file(cls, spec_list: List[Any], response_code_file: str) -> None:

        try:

            with open(response_code_file, "w") as fd:
                json_result = json.dumps([x.to_map() for x in spec_list], indent=4)
                fd.write(json_result)

        except FileNotFoundError as e:

            current_directory = Path(".").resolve()
            print(f"The file you are trying to wright could not be found: \"{cls.parsed_file_location}\"")
            print(f"The current directory is: \"{current_directory}\"")
            raise e

    @classmethod
    def execute(cls, webdriver: WebDriver) -> None:
        content = cls._parse_content(webdriver)
        cls._wright_file(content, cls.parsed_file_location)
        webdriver.quit()

    @classmethod
    def execute_with_command_line_args(cls):

        # parse command line arguments
        website_to_parse = sys.argv[1]
        web_driver_path = sys.argv[2]

        webdriver = Config.get_webdriver(website_to_parse, web_driver_path)
        cls.execute(webdriver)
