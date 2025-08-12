#include "reverse_string.h"
using namespace std;
#include <string>
namespace reverse_string {
  string reverse_string(string str){
      string *output = new string();
       for (std::string::reverse_iterator rit=str.rbegin(); rit!=str.rend(); ++rit)
           *output += *rit;
      return *output;
  }
}  // namespace reverse_string
