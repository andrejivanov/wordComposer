# Code Kata

## run
put your wordlist.txt to ~/. and run
```
./graldew run
```

## run unit tests with small test data
just for TDD and easy refactoring
```
./graldew clean test
```

## Last tests
Giant list tests with 45425 words and 727 possible concatenations.

### Dumb composer
 * Duration: waiting and waiting and waiting, canceled after 30 min.

### Simple optimized composer
With prefiltered lists and parallel streams.
 * Duration 327557 ms ~ 5.5 min. (was not able to use any app on my pc in parallel)

### Optimized composer 
With search tree
Inspired by [solution in ruby](https://github.com/allegrem/agorize-assignment).
 * Duration 69 ms. 
 
 ![very fast](https://files.gamebanana.com/img/ico/sprays/flash_2.png)

