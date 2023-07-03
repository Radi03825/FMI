main :: IO()
main = do
    print (size t1)
    print (height t1)
    print (sumTree t1)
    print (sumLeaves t1)
    print (inorder t1)
    print (average t1)
    print (getLevel 1 t1)
    print (mirrorTree t1)
    print (mapTree (* 2) t1)



data BTree a = Empty | Node a (BTree a) (BTree a)
    deriving Show

t1 :: BTree Int                                        --    5
t1 = Node 5 (Node 2 Empty                              --   / \
                        (Node 3 Empty Empty))          --  2   6
            (Node 6 Empty Empty)                       --   \
                                                       --    3    

t2 :: BTree Int                                        --    5
t2 = Node 5 (Node 3 Empty Empty)                       --   / \
            (Node 4 (Node 5 Empty Empty)               --  3   4
                    (Node 7 Empty Empty))              --     / \
                                                       --    5   7



size :: BTree a -> Int
size Empty = 0
size (Node _ lt rt) = 1 + size lt + size rt

height :: BTree a -> Int
height Empty = 0
height (Node _ lt rt) = 1 + max (height lt) (height rt)

sumTree :: (Num a) => BTree a -> a
sumTree Empty = 0
sumTree (Node v lt rt) = v + sumTree lt + sumTree rt

sumLeaves :: (Num a) => BTree a -> a
sumLeaves Empty = 0
sumLeaves (Node v Empty Empty) = v
sumLeaves (Node _ lt rt) = sumLeaves lt + sumLeaves rt

-- Left - Root - Right
inorder :: BTree a -> [a]
inorder Empty = []
inorder (Node v lt rt) = inorder lt ++ [v] ++ inorder rt

average :: (Num a, Integral a) => BTree a -> Double
average t = fromIntegral (sumTree t) / fromIntegral (size t)

getLevel :: Int -> BTree a -> [a]
getLevel _ Empty = []
getLevel 0 (Node v _ _) = [v]
getLevel n (Node _ lt rt) = getLevel (n - 1) lt ++ getLevel (n - 1) rt

mirrorTree :: BTree a -> BTree a                                        
mirrorTree Empty = Empty
mirrorTree (Node v lt rt) = Node v (mirrorTree rt) (mirrorTree lt)

-- Example:      1                1
--              / \              / \
--             2   3     =>     3   2
--            /   / \          / \   \
--           5   7   6        6   7   5



mapTree :: (a -> b) -> BTree a -> BTree b
mapTree _ Empty = Empty
mapTree f (Node v lt rt) = Node (f v) (mapTree f lt) (mapTree f rt)
