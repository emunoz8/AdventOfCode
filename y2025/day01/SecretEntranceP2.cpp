#include <bits/stdc++.h>
using namespace std;

int main() {
  int currentValue = 50;
  int deltaValue;
  int maxValue = 100;
  int count = 0;
  string line;

  freopen("day01/input.txt", "r", stdin);

  while (getline(cin, line)) {
    int num = stoi(line.substr(1));
    int diff;

    deltaValue = line[0] == 'L' ? -num : num;

    diff = currentValue + deltaValue;

    if (diff >= 100 || diff < 0) count += (deltaValue / maxValue) + 1;

    currentValue = (currentValue + deltaValue) % maxValue;

    if (currentValue == 0) count++;
  }

  cout << count;

  return 0;
}