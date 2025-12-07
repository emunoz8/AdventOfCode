#include <bits/stdc++.h>
using namespace std;

int main() {
  int currentValue = 50;
  int maxValue = 100;
  int count = 0;
  string line;

  freopen("day01/input.txt", "r", stdin);

  while (getline(cin, line)) {
    int num = stoi(line.substr(1));
    char dir = line[0];

    int fullLoops = num / maxValue;
    int rem = num % maxValue;

    int extra = 0;

    int distToZero;
    if (dir == 'R') {
      distToZero = (maxValue - currentValue) % maxValue;
    } else {
      distToZero = currentValue % maxValue;
    }
    if (distToZero == 0) distToZero = maxValue;

    if (rem >= distToZero) extra = 1;

    if (dir == 'R') {
      currentValue = (currentValue + num) % maxValue;
    } else {
      currentValue = (currentValue - num) % maxValue;
      if (currentValue < 0) currentValue += maxValue;
    }

    count += (fullLoops + extra);

    /*
    cout << line << " -> " << currentValue << " $" << (fullLoops + extra)
         << "\n";

         */
  }

  cout << count;
  return 0;
}
