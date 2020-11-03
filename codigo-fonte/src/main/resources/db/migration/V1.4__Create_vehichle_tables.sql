create TABLE IF NOT EXISTS "vehicle" (
    "id" SERIAL PRIMARY KEY,
    "chassi" varchar(30)  NOT NULL,
    "trackerBrand" varchar(30)  NOT NULL,
    "trackerVersion" varchar(30)  NOT NULL,
    "integrationID" varchar(30)  NOT NULL,
    "registration" varchar(30)  NOT NULL
);