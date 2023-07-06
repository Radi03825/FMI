main :: IO()
main = do
    print (listOfIndexes 3 [1,2,3,4,3,5,3,2,1] == [2,4,6])
    print (listOfIndexesWithZip 3 [1,2,3,4,3,5,3,2,1] == [2,4,6])



listOfIndexes :: Int -> [Int] -> [Int]
listOfIndexes n xs = helper 0 xs
  where
    helper _ [] = []
    helper i (x:xs) =
      if (x == n) then i:helper (i + 1) xs
      else helper (i + 1) xs

listOfIndexesWithZip :: Int -> [Int] -> [Int]
listOfIndexesWithZip n xs = [i | (i, x) <- zip [0..] xs, x == n]
