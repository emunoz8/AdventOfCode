#include <bits/stdc++.h>
using namespace std;

template <typename T>
long long iterateThroughNumbers(T num1, T num2) {
  long long sum = 0;
  for (T i = num1; i <= num2; i++) {
    string s = to_string(i);
    if (s.size() % 2 == 0 &&
        s.substr(0, s.size() / 2) == s.substr(s.size() / 2)) {
      sum += i;
      // cout << s << " ::";
      // cout << s.substr(0, s.size() / 2) << " ";
      // cout << s.substr(s.size() / 2) << "\n";
    }
  }
  return sum;
}

long long iterateLine(string line) {
  int start = 0, mid = 0, end = 0;
  long long sum = 0;

  for (size_t i = 0; i < line.size(); i++) {
    mid = start;
    while (i < line.size() && line[i] != '-') {
      mid++;
      i++;
    }

    end = mid;
    mid++;

    while (i < line.size() && line[i] != ',') {
      end++;
      i++;
    }

    if (((mid - start - 1) % 2 != 0 || (end - mid) % 2 != 0) &&
        ((mid - start - 1) == (end - mid))) {
      start = end + 1;
      end = start;
      continue;
    }

    if (mid - start > 9 || end - mid > 9) {
      long long num = stoll(line.substr(start, mid));
      long long num2 = stoll(line.substr(mid, end));
      sum += iterateThroughNumbers(num, num2);
      // cout << num << "\n";
      // cout << num2 << "\n";
    } else {
      int num = stoi(line.substr(start, mid));
      int num2 = stoi(line.substr(mid, end));
      sum += iterateThroughNumbers(num, num2);

      // cout << num << "\n";
      // cout << num2 << "\n";
    }

    start = end + 1;
    end = start;
  }

  return sum;
}

int main() {
  freopen("day02/input.txt", "r", stdin);

  string line;

  while (getline(cin, line)) {
    cout << iterateLine(line);
  }
}