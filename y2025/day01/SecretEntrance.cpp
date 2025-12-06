#include <bits/stdc++.h>
using namespace std;

int main() {
  int currentValue = 50;
  int deltaValue;
  int maxVal = 100;
  int count = 0;
  string line;

  freopen("day01/input.txt", "r", stdin);

  while (getline(cin, line)) {
    int num = stoi(line.substr(1));

    deltaValue = line[0] == 'L' ? -num : num;

    currentValue = ((currentValue + deltaValue)) % maxVal;

    if (currentValue == 0) count++;
  }

  cout << count;

  return 0;
}