import sys

from lib.parser.ResponseCodeParser import ResponseCodeParser
from lib.parser.HeaderParser import HeaderParser
from lib.parser.RequestMethodParser import RequestMethodParser
from lib.config.config import Config


def main():
    header_website = sys.argv[0]
    response_code_website = sys.argv[1]
    request_header_website = sys.argv[2]
    web_driver_path = sys.argv[2]

    HeaderParser.execute(Config.get_webdriver(header_website, web_driver_path))
    ResponseCodeParser.execute(Config.get_webdriver(response_code_website, web_driver_path))
    RequestMethodParser.execute(Config.get_webdriver(request_header_website, web_driver_path))


if __name__ == "__main__":
    main()
