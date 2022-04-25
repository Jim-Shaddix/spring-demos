from typing import Dict

class ResponseCodeSpec:

    def __init__(self, rc_type, rc_name, rc_desc, link):
        self.rc_type = rc_type
        self.rc_name = rc_name
        self.rc_desc = rc_desc
        self.link = link

    def to_map(self) -> Dict[str, str]:
        return {
            "type": self.rc_type,
            "name": self.rc_name,
            "description": self.rc_desc,
            "link": self.link
        }

    def __str__(self):
        return f"ResponseCodeSpec=[type:{self.rc_type}, name:{self.rc_name}, link:{self.link}, description:{self.rc_desc}]"

    def __repr__(self):
        return self.__str__()


