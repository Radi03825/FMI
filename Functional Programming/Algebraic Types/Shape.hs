main :: IO()
main = do
    print (perimeter (Rectangle 10 15))
    print ([perimeter s | s <- ss])
    print (biggestShape [Circle 2, Rectangle 5 10, Triangle 3 4 5, Rectangle 2 5])



data Shape = Circle Double | Rectangle Double Double | Triangle Double Double Double
  deriving (Show, Eq, Ord)

ss :: [Shape]
ss = [Circle 7, Rectangle 10 15, Triangle 3 4 5]



perimeter :: Shape -> Double
perimeter (Circle r) = 3.14 * 2 * r
perimeter (Rectangle a b) = 2 * (a + b)
perimeter (Triangle a b c) = a + b + c

area :: Shape -> Double
area (Circle r) = 3.14 * r * r
area (Rectangle a b) = a * b
area t@(Triangle a b c) = sqrt(p * (p - a) * (p - b) * (p - c))
  where p = (perimeter t) / 2 

isRound :: Shape -> Bool
isRound (Circle _) = True
isRound _ = False

biggestShape :: [Shape] -> Shape
biggestShape = snd . maximum . (map (\ s -> (area s, s)))
