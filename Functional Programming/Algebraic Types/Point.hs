import Data.List

main :: IO()
main = do
    print (getClosestPoint [Point2D 2 5, Point3D 4 2 1, Point2D 3 2] (Point2D 1 1))
    print (distance (Point2D 2 5) (Point2D 5 7))
    print (getClosestDistance [Point2D 2 5, Point3D 4 2 1, Point2D 3 2, Point3D 4 2 2])



data Point = Point2D Double Double | Point3D Double Double Double
  deriving (Show, Eq, Ord)



distance :: Point -> Point -> Double
distance (Point2D a1 b1) (Point2D a2 b2) = sqrt ((a1 - a2) ^ 2 + (b1 - b2) ^ 2)
distance (Point3D a1 b1 c1) (Point3D a2 b2 c2) = sqrt ((a1 - a2) ^ 2 + (b1 - b2) ^ 2 + (c1 - c2) ^ 2)
distance _ _ = error "Not compatible points"

filterByType :: [Point] -> Point -> [Point]
filterByType [] _ = []
filterByType ((Point2D a b):ps) p@(Point2D _ _) = (Point2D a b):filterByType ps p
filterByType ((Point3D a b c):ps) p@(Point3D _ _ _) = (Point3D a b c):filterByType ps p
filterByType (_:ps) p = filterByType ps p

getClosestPoint :: [Point] -> Point -> Point
getClosestPoint qs p = (snd . minimum) [(distance p q, q) | q <- filterByType qs p]

minDistances :: [Point] -> [(Double, Point, Point)]
minDistances ps = [(distance p q, p, q) | p <- ps, q <- delete p ps]

getClosestDistance :: [Point] -> (Double, Point, Point)
getClosestDistance ps = minimum (minDistances (filterByType ps (Point2D 0 0)) ++ minDistances (filterByType ps (Point3D 0 0 0)))
