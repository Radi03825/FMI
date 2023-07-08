main :: IO()
main = do
    print (factorize 13 == [13])
    print (factorize 123 == [3,41])
    print (factorize 152 == [2,2,2,19])



factorize :: Int -> [Int]
factorize n = helper n 2
  where
    helper 1 _ = []
    helper k d =
      if (k `mod` d == 0)
      then d:helper (k `div` d) d
      else helper k (d + 1)